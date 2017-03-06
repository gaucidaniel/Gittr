package com.danielgauci.gittr.ui.splash;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.danielgauci.gittr.R;
import com.danielgauci.gittr.ui.feed.FeedActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.splash_animation_view) LottieFontViewGroup mAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        // Add listener to animation
        mAnimationView.setUpdateListener(new LottieFontViewGroup.AnimationUpdateListener() {
            @Override
            public void onAnimationEnd() {
                // Start main activity
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(SplashActivity.this, FeedActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        finish();
                    }
                }, 1500);
            }
        });

        // Set animator text
        mAnimationView.setText("Gittr");
    }

}
