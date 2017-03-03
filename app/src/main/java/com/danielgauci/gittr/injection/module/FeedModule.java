package com.danielgauci.gittr.injection.module;

import com.danielgauci.gittr.data.DataManager;
import com.danielgauci.gittr.ui.feed.FeedPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by daniel on 3/3/17.
 */

@Module
public class FeedModule {

    @Provides
    @Singleton
    public FeedPresenter providesFeedPresenter(DataManager dataManager){
        return new FeedPresenter(dataManager);
    }
}
