package com.danielgauci.gittr.injection.module;

import android.app.Activity;
import android.content.Context;

import com.danielgauci.gittr.injection.ActivityContext;

import dagger.Module;
import dagger.Provides;

/**
 * Created by daniel on 3/1/17.
 */

@Module
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @Provides
    @ActivityContext
    public Context provideContext(){
        return mActivity;
    }
}
