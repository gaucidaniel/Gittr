package com.danielgauci.gittr.ui.splash;

/**
 * Created by daniel on 3/4/17.
 */

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

public class LottieFontViewGroup extends LinearLayout {

    private AnimationUpdateListener mAnimationUpdateListener;

    @Nullable
    private LottieAnimationView cursorView;

    public LottieFontViewGroup(Context context) {
        super(context);
        init();
    }

    public LottieFontViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LottieFontViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setFocusableInTouchMode(true);
        LottieComposition.Factory.fromAssetFileName(getContext(), "animations/Mobilo/BlinkingCursor.json",
                (LottieComposition composition) -> {
                    cursorView = new LottieAnimationView(getContext());
                    cursorView.setLayoutParams(new LottieFontViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                    ));
                    addView(cursorView);
                });

    }

    public void setText(String text) {
        if (text.length() == 0) {
            if (mAnimationUpdateListener != null) {
                mAnimationUpdateListener.onAnimationEnd();
            }
        } else {
            StringBuilder textBuilder = new StringBuilder(text.toUpperCase());
            final String fileName = "animations/Mobilo/" + textBuilder.charAt(0) + ".json";
            LottieComposition.Factory.fromAssetFileName(getContext(), fileName, (LottieComposition composition) -> {
                addComposition(composition);
                setText(text.substring(1, text.length()));
            });
        }
    }

    public void setUpdateListener(AnimationUpdateListener animationUpdateListener) {
        mAnimationUpdateListener = animationUpdateListener;
    }

    private void addComposition(LottieComposition composition) {
        LottieAnimationView lottieAnimationView = new LottieAnimationView(getContext());
        lottieAnimationView.setLayoutParams(new LottieFontViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        lottieAnimationView.setComposition(composition);
        lottieAnimationView.playAnimation();
        if (cursorView == null) {
            addView(lottieAnimationView);
        } else {
            int index = indexOfChild(cursorView);
            addView(lottieAnimationView, index);
        }
    }

    public interface AnimationUpdateListener {
        void onAnimationEnd();
    }
}