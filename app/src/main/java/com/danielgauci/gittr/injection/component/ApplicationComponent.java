package com.danielgauci.gittr.injection.component;

import android.app.Application;
import android.content.Context;

import com.danielgauci.gittr.Gittr;
import com.danielgauci.gittr.data.DataManager;
import com.danielgauci.gittr.data.remote.GithubService;
import com.danielgauci.gittr.injection.module.ActivityModule;
import com.danielgauci.gittr.injection.module.ApplicationModule;
import com.danielgauci.gittr.injection.module.DataModule;
import com.danielgauci.gittr.injection.module.FeedModule;
import com.danielgauci.gittr.ui.feed.FeedActivity;
import com.danielgauci.gittr.ui.feed.FeedFragment;
import com.danielgauci.gittr.ui.feed.FeedPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by daniel on 3/1/17.
 */

@Singleton
@Component(modules = {ApplicationModule.class, ActivityModule.class, DataModule.class, FeedModule.class})
public interface ApplicationComponent {

    void inject(FeedFragment feedFragment);
}
