package com.zhang.library.animation.creator;

import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

import com.zhang.library.animation.constant.AnimationType;

/**
 * 缩放动画创造器
 *
 * @author ZhangXiaoMing 2021-03-09 21:21 星期二
 */
public class ScaleAnimationCreator extends BaseAnimationCreator implements ICreator<ScaleAnimation> {

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

    public static ScaleAnimationCreator builder(@AnimationType int animationType) {
        ScaleAnimationCreator creator = new ScaleAnimationCreator();
        creator.setAnimationType(animationType);

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

    public ScaleAnimationCreator setPivotXType(Integer pivotXType) {
        this.pivotXType = pivotXType;
        return this;
    }

    public ScaleAnimationCreator setPivotYType(Integer pivotYType) {
        this.pivotYType = pivotYType;
        return this;
    }

    public ScaleAnimationCreator setPivotXValue(Float pivotXValue) {
        this.pivotXValue = pivotXValue;
        return this;
    }

    public ScaleAnimationCreator setPivotYValue(Float pivotYValue) {
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
