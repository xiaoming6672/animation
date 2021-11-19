package com.zhang.library.animation.creator;

import android.view.animation.TranslateAnimation;

/**
 * 移动动画创造器
 *
 * @author ZhangXiaoMing 2021-03-09 17:39 星期二
 */
public class TranslateAnimationCreator extends AnimationCreator<TranslateAnimation> {

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

    public static TranslateAnimationCreator builder(@PivotType int animationType) {
        TranslateAnimationCreator creator = new TranslateAnimationCreator();
        creator.setPivotType(animationType);
        return creator;
    }

    //<editor-fold desc="Setter">

    protected TranslateAnimationCreator setFromXType(@PivotType int fromXType) {
        this.fromXType = fromXType;
        return this;
    }

    public TranslateAnimationCreator setFromXValue(float fromXValue) {
        this.fromXValue = fromXValue;
        return this;
    }

    protected TranslateAnimationCreator setToXType(@PivotType int toXType) {
        this.toXType = toXType;
        return this;
    }

    public TranslateAnimationCreator setToXValue(float toXValue) {
        this.toXValue = toXValue;
        return this;
    }

    protected TranslateAnimationCreator setFromYType(@PivotType int fromYType) {
        this.fromYType = fromYType;
        return this;
    }

    public TranslateAnimationCreator setFromYValue(float fromYValue) {
        this.fromYValue = fromYValue;
        return this;
    }

    protected TranslateAnimationCreator setToYType(@PivotType int toYType) {
        this.toYType = toYType;
        return this;
    }

    public TranslateAnimationCreator setToYValue(float toYValue) {
        this.toYValue = toYValue;
        return this;
    }

    //</editor-fold>

    @Override
    public void setPivotType(@PivotType int pivotType) {
        setFromXType(pivotType);
        setToXType(pivotType);

        setFromYType(pivotType);
        setToYType(pivotType);
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
