package com.zhang.library.animation.creator;

import android.view.animation.AlphaAnimation;

/**
 * 透明度动画创造器
 *
 * @author ZhangXiaoMing 2021-03-09 22:07 星期二
 */
public class AlphaAnimationCreator extends BaseAnimationCreator<AlphaAnimation> {

    private float fromAlpha;
    private float toAlpha;

    private AlphaAnimationCreator() {
    }

    public static AlphaAnimationCreator builder() {
        return new AlphaAnimationCreator();
    }

    //<editor-fold desc="Setter">
    public AlphaAnimationCreator setFromAlpha(float fromAlpha) {
        this.fromAlpha = fromAlpha;
        return this;
    }

    public AlphaAnimationCreator setToAlpha(float toAlpha) {
        this.toAlpha = toAlpha;
        return this;
    }
    //</editor-fold>

    @Override
    public void setAnimationType(int animationType) {
    }

    @Override
    public AlphaAnimation create() {
        AlphaAnimation anim = new AlphaAnimation(fromAlpha, toAlpha);
        setPublicAttribute(anim);

        return anim;
    }
}
