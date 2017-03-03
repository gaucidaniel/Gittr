package com.danielgauci.gittr.injection.module;

import android.app.Application;
import android.content.Context;

import com.danielgauci.gittr.Gittr;
import com.danielgauci.gittr.injection.ApplicationScope;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by daniel on 3/1/17.
 */

@Module
public class ApplicationModule {

    private Gittr mApplication;

    public ApplicationModule(Gittr mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    @Singleton
    public Context provideContext(){
        return mApplication;
    }
}
