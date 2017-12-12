package com.emreatak.android.RadRoid.activities;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.emreatak.android.RadRoid.R;

import gr.net.maroulis.library.EasySplashScreen;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EasySplashScreen config = new EasySplashScreen(SplashActivity.this)
                .withFullScreen()
                .withTargetActivity(MainActivity.class)
                .withSplashTimeOut(4000)
                .withBackgroundResource(R.color.colorAccent)
                .withBeforeLogoText("RadRoid");

        //add custom font
        Typeface Amethyst = Typeface.createFromAsset(getAssets(), "Amethyst.ttf");
        Typeface Roboto = Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf");

        config.getBeforeLogoTextView().setTypeface(Amethyst);

        config.getBeforeLogoTextView().setTextSize(35);
        //change text color

        //finally create the view
        View easySplashScreenView = config.create();
        setContentView(easySplashScreenView);
    }
}
