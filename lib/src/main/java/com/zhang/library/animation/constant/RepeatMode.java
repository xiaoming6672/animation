package com.zhang.library.animation.constant;

import android.view.animation.Animation;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 动画重复模式
 *
 * @author ZhangXiaoMing 2021-03-09 21:01 星期二
 */
@IntDef({RepeatMode.RESTART, RepeatMode.REVERSE})
@Retention(RetentionPolicy.SOURCE)
public @interface RepeatMode {

    int RESTART = Animation.RESTART;
    int REVERSE = Animation.REVERSE;
}
