package com.zhang.library.animation.creator;

import android.view.animation.RotateAnimation;

import com.zhang.library.animation.constant.AnimationType;

/**
 * 旋转动画创造器
 *
 * @author ZhangXiaoMing 2021-03-09 22:11 星期二
 */
public class RotateAnimationCreator extends BaseAnimationCreator<RotateAnimation> {

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

    public static RotateAnimationCreator builder(@AnimationType int animationType) {
        RotateAnimationCreator creator = new RotateAnimationCreator();
        creator.setAnimationType(animationType);

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

    public RotateAnimationCreator setPivotXType(@AnimationType Integer pivotXType) {
        this.pivotXType = pivotXType;
        return this;
    }

    public RotateAnimationCreator setPivotYType(@AnimationType Integer pivotYType) {
        this.pivotYType = pivotYType;
        return this;
    }

    public RotateAnimationCreator setPivotXValue(Float pivotXValue) {
        this.pivotXValue = pivotXValue;
        return this;
    }

    public RotateAnimationCreator setPivotYValue(Float pivotYValue) {
        this.pivotYValue = pivotYValue;
        return this;
    }
    //</editor-fold>

    @Override
    public void setAnimationType(@AnimationType int animationType) {
        this.pivotXType = animationType;
        this.pivotYType = animationType;
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
