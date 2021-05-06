package com.zhang.library.animation.creator;

import android.view.animation.Animation;
import android.view.animation.Interpolator;

import com.zhang.library.animation.constant.RepeatMode;

/**
 * 基类动画创造器，用于设置公共方法
 *
 * @author ZhangXiaoMing 2021-03-09 17:24 星期二
 */
public abstract class BaseAnimationCreator<T extends Animation> implements ICreator<T> {

    /** 动画持续时间 */
    protected Long duration;
    /** 动画重复次数 */
    protected Integer repeatCount;
    /** 动画重复模式 */
    protected Integer repeatMode;
    /** 动画播放插补器 */
    protected Interpolator interpolator;
    /** 动画播放监听 */
    protected Animation.AnimationListener listener;
    /** 动画播放延时 */
    protected Long startOffset;
    /**
     * 设置动画是否启动停留帧
     *
     * @see Animation#setFillEnabled(boolean)
     */
    protected Boolean fillEnabled;
    /**
     * 停留显示动画第一帧
     *
     * @see Animation#setFillBefore(boolean)
     */
    protected Boolean fillBefore;
    /**
     * 停留显示动画最后一帧
     *
     * @see Animation#setFillAfter(boolean)
     */
    protected Boolean fillAfter;

    protected BaseAnimationCreator() {
    }

    //<editor-fold desc="Setter">
    public BaseAnimationCreator<T> setDuration(Long duration) {
        this.duration = duration;
        return this;
    }

    public BaseAnimationCreator<T> setRepeatCount(Integer repeatCount) {
        this.repeatCount = repeatCount;
        return this;
    }

    public BaseAnimationCreator<T> setRepeatMode(@RepeatMode Integer repeatMode) {
        this.repeatMode = repeatMode;
        return this;
    }

    public BaseAnimationCreator<T> setInterpolator(Interpolator interpolator) {
        this.interpolator = interpolator;
        return this;
    }

    public BaseAnimationCreator<T> setListener(Animation.AnimationListener listener) {
        this.listener = listener;
        return this;
    }

    public BaseAnimationCreator<T> setStartOffset(Long startOffset) {
        this.startOffset = startOffset;
        return this;
    }

    public BaseAnimationCreator<T> setFillEnabled(Boolean fillEnabled) {
        this.fillEnabled = fillEnabled;
        return this;
    }

    public BaseAnimationCreator<T> setFillBefore(Boolean fillBefore) {
        this.fillBefore = fillBefore;
        return this;
    }

    public BaseAnimationCreator<T> setFillAfter(Boolean fillAfter) {
        this.fillAfter = fillAfter;
        return this;
    }
    //</editor-fold>

    /** 设置公共方法参数 */
    protected void setPublicAttribute(Animation anim) {
        if (duration != null) {
            anim.setDuration(duration);
        }

        if (repeatCount != null) {
            anim.setRepeatCount(repeatCount);
        }

        if (repeatMode != null) {
            anim.setRepeatMode(repeatMode);
        }

        if (interpolator != null) {
            anim.setInterpolator(interpolator);
        }

        if (listener != null) {
            anim.setAnimationListener(listener);
        }

        if (startOffset != null) {
            anim.setStartOffset(startOffset);
        }

        if (fillAfter != null) {
            anim.setFillAfter(fillAfter);
        }

        if (fillBefore != null) {
            anim.setFillBefore(fillBefore);
        }

        if (fillEnabled != null) {
            anim.setFillEnabled(fillEnabled);
        }
    }

}
