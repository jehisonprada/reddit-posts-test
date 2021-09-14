package com.rappi.test.redditpostsviewer.view;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.rappi.test.redditpostsviewer.R;
import com.rappi.test.redditpostsviewer.presenter.SplashPresenter;
import com.rappi.test.redditpostsviewer.presenter.SplashPresenterImpl;
import com.rappi.test.redditpostsviewer.util.DimensionsUtil;

public class SplashActivity extends Activity implements SplashView,Animator.AnimatorListener {

    private SplashPresenter splashPresenter;

    private ImageView logoImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        logoImage = (ImageView)findViewById(R.id.logo_for_splash);
        splashPresenter = new SplashPresenterImpl(this);
        startSplashAnimation();
    }

    public void startSplashAnimation() {
        if(DimensionsUtil.isTablet(this)){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        } else{
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
        }
        int endXPosition = DimensionsUtil.getScreenSize(this).widthPixels;
        ObjectAnimator animation = ObjectAnimator.ofFloat(logoImage,"translationX",0,endXPosition);
        animation.addListener(this);
        animation.setDuration(3000);
        animation.start();
    }

    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {
        Intent thumbnailIntent = new Intent().setClass(SplashActivity.this, ThumbnailActivity.class);
        startActivity(thumbnailIntent);
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }
}