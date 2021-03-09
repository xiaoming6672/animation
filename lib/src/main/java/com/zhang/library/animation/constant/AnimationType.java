package com.zhang.library.animation.constant;

import android.view.animation.Animation;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author ZhangXiaoMing 2021-03-09 20:48 星期二
 */

@IntDef({AnimationType.ABSOLUTE, AnimationType.SELF, AnimationType.PARENT})
@Retention(RetentionPolicy.SOURCE)
public @interface AnimationType {
    /** 绝对类型：此时指定的X、Y则是绝对的像素值 */
    int ABSOLUTE = Animation.ABSOLUTE;
    /** 相对自身类型：此时指定的X、Y则是相对自己身的百分比 */
    int SELF = Animation.RELATIVE_TO_SELF;
    /** 相对父控件类型：此时指定的X、Y则是相对父控件的百分比 */
    int PARENT = Animation.RELATIVE_TO_PARENT;
}
