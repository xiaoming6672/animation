package com.zhang.library.animation;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import androidx.appcompat.app.AppCompatActivity;

import com.zhang.library.animation.array.ArrayAnimation;
import com.zhang.library.animation.constant.AnimationType;
import com.zhang.library.animation.constant.RepeatMode;
import com.zhang.library.animation.creator.ScaleAnimationCreator;
import com.zhang.library.animation.creator.TranslateAnimationCreator;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View v) {
        ScaleAnimation scale = ScaleAnimationCreator.builder(AnimationType.SELF)
                .setPivotXValue(0.5F)
                .setPivotYValue(0.5F)
                .setFromX(1F)
                .setToX(2F)
                .setFromY(1F)
                .setToY(2F)
                .setDuration(1000L)
                .setRepeatCount(1)
                .setRepeatMode(RepeatMode.REVERSE)
                .setInterpolator(new LinearInterpolator())
                .create();

        TranslateAnimation translate = TranslateAnimationCreator.builder(AnimationType.SELF)
                .setFromXValue(0F)
                .setToXValue(1F)
                .setFromYValue(0F)
                .setToYValue(5F)
                .setDuration(1500L)
                .setRepeatCount(Animation.INFINITE)
                .setRepeatMode(RepeatMode.REVERSE)
                .setInterpolator(new AccelerateInterpolator())
                .create();

        ArrayAnimation arrayAnim = new ArrayAnimation()
                .setTarget(v)
                .setRepeatCount(2)
                .addAnimation(scale)
                .addAnimation(translate)
                .setShareInterpolator(true)
//                .setInterpolator(new DecelerateInterpolator())
                ;

        arrayAnim.setArrayAnimationCallback(new ArrayAnimation.ArrayAnimationCallback() {
            @Override
            public void onAnimStart(ArrayAnimation arrayAnim) {
                Log.i(TAG, "onAnimStart()");
            }

            @Override
            public void onAnimRepeat(ArrayAnimation arrayAnim, int hasLoopCount) {
                Log.d(TAG, "onAnimRepeat()======hasLoopCount = " + hasLoopCount);
            }

            @Override
            public void onAnimEnd(ArrayAnimation arrayAnim) {
                Log.e(TAG, "onAnimEnd()");
            }
        });
        arrayAnim.start();
    }
}