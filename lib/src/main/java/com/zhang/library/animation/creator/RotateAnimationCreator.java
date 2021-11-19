package com.zhang.library.animation.creator;

import android.view.animation.RotateAnimation;

/**
 * 旋转动画创造器
 *
 * @author ZhangXiaoMing 2021-03-09 22:11 星期二
 */
public class RotateAnimationCreator extends AnimationCreator<RotateAnimation> {

    /** 起始度数 */
    private float fromDegrees;
    /** 结束度数 */
    private float toDegrees;

    /** X旋转类型 */
    private Integer pivotXType;
    /** Y旋转类型 */
    private Integer pivotYType;

    /** X旋转中心位置 */
    private Float pivotXValue;
    /** Y旋转中心位置 */
    private Float pivotYValue;

    private RotateAnimationCreator() {
    }

    public static RotateAnimationCreator builder() {
        return new RotateAnimationCreator();
    }

    public static RotateAnimationCreator builder(@PivotType int animationType) {
        RotateAnimationCreator creator = new RotateAnimationCreator();
        creator.setPivotType(animationType);

        return creator;
    }

    //<editor-fold desc="Setter">
    public RotateAnimationCreator setFromDegrees(float fromDegrees) {
        this.fromDegrees = fromDegrees;
        return this;
    }

    public RotateAnimationCreator setToDegrees(float toDegrees) {
        this.toDegrees = toDegrees;
        return this;
    }

    protected RotateAnimationCreator setPivotXType(@PivotType int pivotXType) {
        this.pivotXType = pivotXType;
        return this;
    }

    protected RotateAnimationCreator setPivotYType(@PivotType int pivotYType) {
        this.pivotYType = pivotYType;
        return this;
    }

    /** 设置旋转中心点为控件相对自身中心 */
    public RotateAnimationCreator setPivotSelfCenter() {
        return setPivotXType(SELF)
                .setPivotYType(SELF)
                .setPivotValue(0.5F, 0.5F);
    }

    /**
     * 设置旋转中心点
     *
     * @param pivotXValue X旋转中心位置
     * @param pivotYValue Y旋转中心位置
     */
    public RotateAnimationCreator setPivotValue(float pivotXValue, float pivotYValue) {
        return setPivotXValue(pivotXValue)
                .setPivotYValue(pivotYValue);
    }

    protected RotateAnimationCreator setPivotXValue(float pivotXValue) {
        this.pivotXValue = pivotXValue;
        return this;
    }

    protected RotateAnimationCreator setPivotYValue(float pivotYValue) {
        this.pivotYValue = pivotYValue;
        return this;
    }
    //</editor-fold>

    @Override
    public void setPivotType(@PivotType int pivotType) {
        setPivotXType(pivotType);
        setPivotYType(pivotType);
    }

    @Override
    public RotateAnimation create() {
        RotateAnimation anim;

        if (pivotXType != null && pivotYType != null
                && pivotXValue != null && pivotYValue != null) {
            anim = new RotateAnimation(fromDegrees, toDegrees, pivotXType, pivotXValue, pivotYType, pivotYValue);
        } else if (pivotXValue != null && pivotYValue != null) {
            anim = new RotateAnimation(fromDegrees, toDegrees, pivotXValue, pivotYValue);
        } else {
            anim = new RotateAnimation(fromDegrees, toDegrees);
        }

        setPublicAttribute(anim);

        return anim;
    }
}
