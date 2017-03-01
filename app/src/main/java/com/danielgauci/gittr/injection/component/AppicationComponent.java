package com.danielgauci.gittr.injection.component;

import com.danielgauci.gittr.injection.module.ApplicationModule;
import com.danielgauci.gittr.ui.feed.FeedActivity;
import com.danielgauci.gittr.ui.feed.FeedFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by daniel on 3/1/17.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface AppicationComponent {

    void inject(FeedActivity feedActivity);
    void inject(FeedFragment feedFragment);
}
