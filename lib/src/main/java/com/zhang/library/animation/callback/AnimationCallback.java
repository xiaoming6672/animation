package com.zhang.library.animation.callback;

import android.view.animation.Animation;

/**
 * 抽象类实现动画监听，需要的时候只要重写对应的方法即可
 *
 * @author ZhangXiaoMing 2021-03-09 17:28 星期二
 */
public abstract class AnimationCallback implements Animation.AnimationListener {

    @Override
    public void onAnimationStart(Animation animation) {
    }

    @Override
    public void onAnimationEnd(Animation animation) {
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
    }
}
