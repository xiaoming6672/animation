package com.zhang.library.animation.creator;

import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 基类动画创造器，用于设置公共方法
 *
 * @author ZhangXiaoMing 2021-03-09 17:24 星期二
 */
public abstract class AnimationCreator<T extends Animation> implements ICreator<T> {

    /** 动画持续时间 */
    protected long duration;
    /** 动画重复次数 */
    protected int repeatCount;
    /** 动画重复模式 */
    protected int repeatMode;
    /** 动画播放插补器 */
    protected Interpolator interpolator;
    /** 动画播放监听 */
    protected Animation.AnimationListener listener;
    /** 动画播放延时 */
    protected long startOffset;
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


    /** 绝对类型：此时指定的X、Y则是绝对的像素值 */
    public static final int ABSOLUTE = Animation.ABSOLUTE;
    /** 相对自身类型：此时指定的X、Y则是相对自己身的百分比 */
    public static final int SELF = Animation.RELATIVE_TO_SELF;
    /** 相对父控件类型：此时指定的X、Y则是相对父控件的百分比 */
    public static final int PARENT = Animation.RELATIVE_TO_PARENT;

    /** @hide */
    @IntDef({ABSOLUTE, SELF, PARENT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface PivotType {
    }

    /** 重新开始 */
    public static final int RESTART = Animation.RESTART;
    /** 逆转倒放 */
    public static final int REVERSE = Animation.REVERSE;

    /** @hide */
    @IntDef({RESTART, REVERSE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface RepeatMode {
    }


    protected AnimationCreator() {
    }

    //<editor-fold desc="Setter">

    /** 设置动画持续时间 */
    public AnimationCreator<T> setDuration(long duration) {
        this.duration = duration;
        return this;
    }

    /**
     * 设置动画重复次数，重复模式为重新开始
     *
     * @param repeatCount 重复次数
     */
    public AnimationCreator<T> setAnimRepeat(int repeatCount) {
        return setAnimRepeat(repeatCount, RESTART);
    }

    /**
     * 设置动画无限播放
     *
     * @param repeatMode 重复模式
     */
    public AnimationCreator<T> setAnimRepeatInfinite(@RepeatMode int repeatMode) {
        return setAnimRepeat(Animation.INFINITE, repeatMode);
    }

    /**
     * 设置动画重复次数
     *
     * @param repeatCount 重复次数
     * @param repeatMode  重复模式
     */
    public AnimationCreator<T> setAnimRepeat(int repeatCount, @RepeatMode int repeatMode) {
        return setRepeatCount(repeatCount)
                .setRepeatMode(repeatMode);
    }

    /** 设置动画重复次数 */
    protected AnimationCreator<T> setRepeatCount(int repeatCount) {
        this.repeatCount = repeatCount;
        return this;
    }

    /** 设置动画重复模式 */
    protected AnimationCreator<T> setRepeatMode(@RepeatMode int repeatMode) {
        this.repeatMode = repeatMode;
        return this;
    }

    /** 设置动画播放插补器 */
    public AnimationCreator<T> setInterpolator(Interpolator interpolator) {
        this.interpolator = interpolator;
        return this;
    }

    /** 设置动画播放监听 */
    public AnimationCreator<T> setListener(Animation.AnimationListener listener) {
        this.listener = listener;
        return this;
    }

    /** 设置动画播放延时 */
    public AnimationCreator<T> setStartOffset(long startOffset) {
        this.startOffset = startOffset;
        return this;
    }

    /**
     * 设置动画是否启动停留帧
     *
     * @see Animation#setFillEnabled(boolean)
     */
    public AnimationCreator<T> setFillEnabled(Boolean fillEnabled) {
        this.fillEnabled = fillEnabled;
        return this;
    }

    /**
     * 停留显示动画第一帧
     *
     * @see Animation#setFillBefore(boolean)
     */
    public AnimationCreator<T> setFillBefore(Boolean fillBefore) {
        this.fillBefore = fillBefore;
        return this;
    }

    /**
     * 停留显示动画最后一帧
     *
     * @see Animation#setFillAfter(boolean)
     */
    public AnimationCreator<T> setFillAfter(Boolean fillAfter) {
        this.fillAfter = fillAfter;
        return this;
    }
    //</editor-fold>

    /** 设置公共方法参数 */
    protected void setPublicAttribute(Animation anim) {
        anim.setDuration(duration);
        anim.setStartOffset(startOffset);

        anim.setRepeatCount(repeatCount);
        anim.setRepeatMode(repeatMode);

        anim.setInterpolator(interpolator == null ? new LinearInterpolator() : interpolator);

        anim.setFillBefore(fillBefore == null ? true : fillBefore);
        anim.setFillAfter(fillAfter == null ? false : fillAfter);
        anim.setFillEnabled(fillEnabled == null ? false : fillEnabled);

        if (listener != null)
            anim.setAnimationListener(listener);
    }

}
