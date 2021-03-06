package com.danielgauci.gittr;

import android.app.Application;

import com.danielgauci.gittr.injection.component.ApplicationComponent;
import com.danielgauci.gittr.injection.component.DaggerApplicationComponent;
import com.danielgauci.gittr.injection.module.ApplicationModule;

/**
 * Created by daniel on 3/1/17.
 */

public class Gittr extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        // Create Dagger app component
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getAppComponent(){
        return mApplicationComponent;
    }
}
