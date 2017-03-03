package com.danielgauci.gittr.injection.module;

import com.danielgauci.gittr.data.DataManager;
import com.danielgauci.gittr.injection.ViewScope;
import com.danielgauci.gittr.ui.feed.FeedPresenter;

import org.ocpsoft.prettytime.PrettyTime;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by daniel on 3/3/17.
 */

@Module
public class ViewModule {

    @Provides
    @Singleton
    public FeedPresenter providesFeedPresenter(DataManager dataManager){
        return new FeedPresenter(dataManager);
    }

    @Provides
    @Singleton
    public PrettyTime providesPrettyTime(){
        return new PrettyTime();
    }
}
