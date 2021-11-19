package com.zhang.library.animation.creator;

import android.view.animation.ScaleAnimation;

/**
 * 缩放动画创造器
 *
 * @author ZhangXiaoMing 2021-03-09 21:21 星期二
 */
public class ScaleAnimationCreator extends AnimationCreator<ScaleAnimation> {

    /** X起始伸缩值 */
    private float fromX;
    /** X结束伸缩值 */
    private float toX;
    /** Y起始伸缩值 */
    private float fromY;
    /** Y结束伸缩值 */
    private float toY;

    /** X伸缩中心点类型 */
    private Integer pivotXType;
    /** Y伸缩中心点类型 */
    private Integer pivotYType;
    /** X伸缩中心位置 */
    private Float pivotXValue;
    /** Y伸缩中心位置 */
    private Float pivotYValue;

    private ScaleAnimationCreator() {
    }

    public static ScaleAnimationCreator builder() {
        return new ScaleAnimationCreator();
    }

    public static ScaleAnimationCreator builder(@PivotType int animationType) {
        ScaleAnimationCreator creator = new ScaleAnimationCreator();
        creator.setPivotType(animationType);

        return creator;
    }

    //<editor-fold desc="Setter">
    public ScaleAnimationCreator setFromX(float fromX) {
        this.fromX = fromX;
        return this;
    }

    public ScaleAnimationCreator setToX(float toX) {
        this.toX = toX;
        return this;
    }

    public ScaleAnimationCreator setFromY(float fromY) {
        this.fromY = fromY;
        return this;
    }

    public ScaleAnimationCreator setToY(float toY) {
        this.toY = toY;
        return this;
    }

    protected ScaleAnimationCreator setPivotXType(@PivotType int pivotXType) {
        this.pivotXType = pivotXType;
        return this;
    }

    protected ScaleAnimationCreator setPivotYType(@PivotType int pivotYType) {
        this.pivotYType = pivotYType;
        return this;
    }

    /** 设置旋转中心点为控件相对自身中心 */
    public ScaleAnimationCreator setPivotSelfCenter() {
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
    public ScaleAnimationCreator setPivotValue(float pivotXValue, float pivotYValue) {
        return setPivotXValue(pivotXValue)
                .setPivotYValue(pivotYValue);
    }

    protected ScaleAnimationCreator setPivotXValue(float pivotXValue) {
        this.pivotXValue = pivotXValue;
        return this;
    }

    protected ScaleAnimationCreator setPivotYValue(float pivotYValue) {
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
    public ScaleAnimation create() {
        ScaleAnimation anim;

        if (pivotXType != null && pivotYType != null
                && pivotXValue != null && pivotYValue != null) {
            anim = new ScaleAnimation(fromX, toX, fromY, toY, pivotXType, pivotXValue, pivotYType, pivotYValue);
        } else if (pivotXValue != null && pivotYValue != null) {
            anim = new ScaleAnimation(fromX, toX, fromY, toY, pivotXValue, pivotYValue);
        } else {
            anim = new ScaleAnimation(fromX, toX, fromY, toY);
        }

        setPublicAttribute(anim);

        return anim;
    }
}
