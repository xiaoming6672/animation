package com.zhang.library.animation.creator;

import android.view.animation.TranslateAnimation;

import com.zhang.library.animation.constant.AnimationType;

/**
 * 移动动画创造器
 *
 * @author ZhangXiaoMing 2021-03-09 17:39 星期二
 */
public class TranslateAnimationCreator extends BaseAnimationCreator
        implements ICreator<TranslateAnimation> {

    /** 设置X起始类型 */
    private Integer fromXType;
    /** 设置X起始位置 */
    private float fromXValue;
    /** 设置X结束类型 */
    private Integer toXType;
    /** 设置X结束位置 */
    private float toXValue;
    /** 设置Y起始类型 */
    private Integer fromYType;
    /** 设置Y起始位置 */
    private float fromYValue;
    /** 设置Y结束类型 */
    private Integer toYType;
    /** 设置Y结束位置 */
    private float toYValue;

    private TranslateAnimationCreator() {
    }

    public static TranslateAnimationCreator builder() {
        return new TranslateAnimationCreator();
    }

    public static TranslateAnimationCreator builder(@AnimationType int animationType) {
        TranslateAnimationCreator creator = new TranslateAnimationCreator();
        creator.setAnimationType(animationType);
        return creator;
    }

    //<editor-fold desc="Setter">

    public TranslateAnimationCreator setFromXType(Integer fromXType) {
        this.fromXType = fromXType;
        return this;
    }

    public TranslateAnimationCreator setFromXValue(float fromXValue) {
        this.fromXValue = fromXValue;
        return this;
    }

    public TranslateAnimationCreator setToXType(Integer toXType) {
        this.toXType = toXType;
        return this;
    }

    public TranslateAnimationCreator setToXValue(float toXValue) {
        this.toXValue = toXValue;
        return this;
    }

    public TranslateAnimationCreator setFromYType(Integer fromYType) {
        this.fromYType = fromYType;
        return this;
    }

    public TranslateAnimationCreator setFromYValue(float fromYValue) {
        this.fromYValue = fromYValue;
        return this;
    }

    public TranslateAnimationCreator setToYType(Integer toYType) {
        this.toYType = toYType;
        return this;
    }

    public TranslateAnimationCreator setToYValue(float toYValue) {
        this.toYValue = toYValue;
        return this;
    }

    //</editor-fold>

    @Override
    public void setAnimationType(@AnimationType int animationType) {
        this.fromXType
                = this.toXType
                = this.fromYType
                = this.toYType
                = animationType;
    }

    @Override
    public TranslateAnimation create() {
        TranslateAnimation anim;

        if (fromXType == null || toXType == null || fromYType == null || toYType == null) {
            anim = new TranslateAnimation(fromXValue, toXValue, fromYValue, toYValue);
        } else {
            anim = new TranslateAnimation(fromXType, fromXValue,
                    toXType, toXValue,
                    fromYType, fromYValue,
                    toYType, toYValue);
        }

        setPublicAttribute(anim);

        return anim;
    }
}
