package com.zhang.library.animation.array;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Interpolator;

import com.zhang.library.animation.callback.AnimationCallback;
import com.zhang.library.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Animation队列播放，针对一个控件，按顺序播放列表中的动画
 *
 * @author ZhangXiaoMing 2021-03-28 20:34 星期日
 */
public class ArrayAnimation {

    /** 播放动画的控件 */
    private View mTarget;
    /** 动画列表 */
    private List<Animation> mAnimList;
    /** 动画队列重复次数 */
    private int mRepeatCount;
    /** 动画共享速度插补器 */
    private boolean mShareInterpolator;
    /** 动画速度插补器 */
    private Interpolator mInterpolator;
    private ArrayAnimationCallback mCallback;

    /** 当前播放位置 */
    private int mIndex;
    /** 已经播放的轮数 */
    private int mLoopCount;
    /** 是否正在播放动画 */
    private boolean isAnimating;
    /** 中断动画播放 */
    private boolean interrupt;
    /** 当前正在播放的动画 */
    private Animation mCurrent;

    public ArrayAnimation() {
        this.mShareInterpolator = false;
        mAnimList = new ArrayList<>();
    }

    /** 设置播放动画的控件 */
    public ArrayAnimation setTarget(View target) {
        this.mTarget = target;
        return this;
    }

    /** 设置队列动画重复轮次 */
    public ArrayAnimation setRepeatCount(int repeatCount) {
        this.mRepeatCount = repeatCount;
        return this;
    }

    /** 获取队列动画重复轮次 */
    public int getRepeatCount() {
        return mRepeatCount;
    }

    /** 设置队列动画是否共享同一个速度插补器 */
    public ArrayAnimation setShareInterpolator(boolean shareInterpolator) {
        this.mShareInterpolator = shareInterpolator;
        return this;
    }

    /** 获取设置：队列动画是否共享同一个速度插补器 */
    public boolean isShareInterpolator() {
        return mShareInterpolator;
    }

    /** 设置动画速度插补器 */
    public ArrayAnimation setInterpolator(Interpolator interpolator) {
        this.mInterpolator = interpolator;
        return this;
    }

    /** 获取动画速度插补器 */
    public Interpolator getInterpolator() {
        return mInterpolator;
    }

    /** 添加动画 */
    public ArrayAnimation addAnimation(Animation anim) {
        //如果单个动画无线循环播放，队列动画将卡在这个动画这里
        if (anim.getRepeatCount() == Animation.INFINITE) {
            anim.setRepeatCount(1);
        }

        mAnimList.add(anim);
        return this;
    }

    /** 移除动画 */
    public ArrayAnimation removeAnimation(Animation anim) {
        mAnimList.remove(anim);
        return this;
    }

    public void setArrayAnimationCallback(ArrayAnimationCallback callback) {
        this.mCallback = callback;
    }

    /** 是否正在播放动画 */
    public boolean isAnimating() {
        return isAnimating;
    }

    public void stop() {
        if (!isAnimating())
            return;

        interrupt = true;

        if (mCurrent != null) {
            mCurrent.cancel();
        }

        if (mTarget != null) {
            mTarget.clearAnimation();
        }
    }

    public void start() {
        if (mTarget == null || CollectionUtils.isEmpty(mAnimList)) {
            throw new IllegalArgumentException("Please set up the view to play the animation first or add the animation to be played!");
        }

        mIndex = 0;
        mLoopCount = 0;

        playAnimation(mIndex);
    }

    private void playAnimation(final int index) {
        if (interrupt) {
            interrupt = false;

            mIndex = 0;
            mLoopCount = 0;
            return;
        }

        final int position = getPosition(index);

        Animation anim = CollectionUtils.get(mAnimList, position);
        if (anim == null)
            return;

        mCurrent = anim;

        processAnimInterpolator(anim);
        anim.setAnimationListener(new AnimationCallback() {
            @Override
            public void onAnimationStart(Animation animation) {
                if (position == 0) {
                    if (index == 0) {
                        if (mCallback != null) {
                            mCallback.onAnimStart(ArrayAnimation.this);
                        }
                    } else {
                        if (mCallback != null) {
                            mCallback.onAnimRepeat(ArrayAnimation.this, mLoopCount);
                        }
                    }
                }


                isAnimating = true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (!isOnceEnd()) {
                    mIndex++;
                    playAnimation(mIndex);
                    return;
                }

                boolean next = hasNextLoop();
                if (next) {
                    mIndex++;
                    mLoopCount++;
                    playAnimation(mIndex);

                    return;
                }

                isAnimating = true;
                mTarget.clearAnimation();

                if (mCallback != null) {
                    mCallback.onAnimEnd(ArrayAnimation.this);
                }
            }
        });

        mTarget.clearAnimation();
        mTarget.startAnimation(anim);
    }

    private void processAnimInterpolator(Animation anim) {
        //共享速度插补器逻辑
        if (mShareInterpolator) {
            if (mInterpolator != null) {
                anim.setInterpolator(mInterpolator);
            } else {
                Animation animation = CollectionUtils.get(mAnimList, 0);
                if (animation != null) {
                    mInterpolator = animation.getInterpolator();
                } else {
                    mInterpolator = anim.getInterpolator();
                }

                anim.setInterpolator(mInterpolator);
            }
        }
    }

    private int getPosition(final int index) {
        return index % CollectionUtils.getSize(mAnimList);
    }

    /** 是否播放到当前轮的最后一个动画 */
    private boolean isOnceEnd() {
        int position = getPosition(mIndex);
        return position == CollectionUtils.getSize(mAnimList) - 1;
    }

    /** 是否有下一轮播放 */
    private boolean hasNextLoop() {
        if (mRepeatCount == Animation.INFINITE) {
            return true;
        }

        if (mRepeatCount == 0) {
            return false;
        }

        return mLoopCount < mRepeatCount;
    }

    /** 队列动画播放监听回调 */
    public interface ArrayAnimationCallback {

        /** 队列动画开始播放 */
        void onAnimStart(ArrayAnimation arrayAnim);

        /**
         * 队列动画重复播放
         *
         * @param hasLoopCount 已经播放轮次计数
         */
        void onAnimRepeat(ArrayAnimation arrayAnim, int hasLoopCount);

        /** 队列动画播放完毕 */
        void onAnimEnd(ArrayAnimation arrayAnim);
    }

}
