package com.zhang.library.animation;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import com.zhang.library.animation.array.ArrayAnimation;
import com.zhang.library.animation.creator.AnimationCreator;
import com.zhang.library.animation.creator.RotateAnimationCreator;
import com.zhang.library.animation.creator.ScaleAnimationCreator;
import com.zhang.library.animation.creator.TranslateAnimationCreator;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.tv_text).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                RotateAnimation rotate = RotateAnimationCreator.builder(AnimationCreator.SELF)
                        .setPivotSelfCenter()
                        .setFromDegrees(-10)
                        .setToDegrees(10)
                        .setDuration(500)
                        .setAnimRepeat(3, AnimationCreator.REVERSE)
//                        .setInterpolator(new AccelerateInterpolator())
                        .create();

                ArrayAnimation arrayAnimation = new ArrayAnimation()
                        .setTarget(v)
                        .setRepeatCount(Animation.INFINITE)
                        .addAnimation(rotate)
                        ;

                v.clearAnimation();
                arrayAnimation.start();
                return true;
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getTag() != null) {
            ArrayAnimation arrayAnim = (ArrayAnimation) v.getTag();
            arrayAnim.stop();

            v.setTag(null);
            return;
        }

        ScaleAnimation scale = ScaleAnimationCreator.builder(AnimationCreator.SELF)
                .setPivotSelfCenter()
                .setFromX(1F)
                .setToX(2F)
                .setFromY(1F)
                .setToY(2F)
                .setDuration(1000L)
                .setAnimRepeat(1, AnimationCreator.REVERSE)
                .create();

        TranslateAnimation translate = TranslateAnimationCreator.builder(AnimationCreator.SELF)
                .setFromXValue(0F)
                .setToXValue(1F)
                .setFromYValue(0F)
                .setToYValue(5F)
                .setDuration(1500L)
                .setAnimRepeat(Animation.INFINITE, AnimationCreator.REVERSE)
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

        v.clearAnimation();
        arrayAnim.start();

        v.setTag(arrayAnim);
    }
}