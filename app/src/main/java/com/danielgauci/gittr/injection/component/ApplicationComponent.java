package com.danielgauci.gittr.injection.component;

import com.danielgauci.gittr.injection.ApplicationScope;
import com.danielgauci.gittr.injection.module.ApplicationModule;
import com.danielgauci.gittr.injection.module.DataModule;
import com.danielgauci.gittr.injection.module.ViewModule;
import com.danielgauci.gittr.ui.feed.FeedFragment;
import com.danielgauci.gittr.ui.search.SearchActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by daniel on 3/1/17.
 */

@Singleton
@Component(modules = { ApplicationModule.class, DataModule.class, ViewModule.class })
public interface ApplicationComponent {

    void inject(FeedFragment feedFragment);
    void inject(SearchActivity searchActivity);
}
