package com.zhang.library.animation;

import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;

import com.zhang.library.animation.constant.AnimationType;
import com.zhang.library.animation.constant.RepeatMode;
import com.zhang.library.animation.creator.AlphaAnimationCreator;
import com.zhang.library.animation.creator.TranslateAnimationCreator;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TranslateAnimation animation = TranslateAnimationCreator.builder(AnimationType.SELF)
                .setFromXValue(0F)
                .setToXValue(1F)
                .setFromYValue(0F)
                .setToYValue(5F)
                .setDuration(1500L)
                .setRepeatCount(Animation.INFINITE)
                .setRepeatMode(RepeatMode.REVERSE)
                .setInterpolator(new LinearInterpolator())
                .create();

        findViewById(R.id.tv_text).startAnimation(animation);
    }
}