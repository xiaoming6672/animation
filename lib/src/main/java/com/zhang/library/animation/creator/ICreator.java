package com.zhang.library.animation.creator;

import android.view.animation.Animation;


/**
 * 创建动画对象接口
 *
 * @author ZhangXiaoMing 2021-03-09 17:58 星期二
 */
public interface ICreator<T extends Animation> {

    /** 统一设置动画的X、Y的类型 */
    void setPivotType(@AnimationCreator.PivotType int pivotType);

    /** 创建动画对象 */
    T create();
}
