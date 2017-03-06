package com.danielgauci.gittr.injection.module;

import android.content.Context;

import com.danielgauci.gittr.data.DataManager;
import com.danielgauci.gittr.injection.ViewScope;
import com.danielgauci.gittr.ui.common.EventsAdapter;
import com.danielgauci.gittr.ui.feed.FeedPresenter;
import com.danielgauci.gittr.ui.search.SearchMvpView;
import com.danielgauci.gittr.ui.search.SearchPresenter;

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
    public SearchPresenter providesSearchPresenter(DataManager dataManager){
        return new SearchPresenter(dataManager);
    }

    @Provides
    @Singleton
    public PrettyTime providesPrettyTime(){
        return new PrettyTime();
    }

    @Provides
    public EventsAdapter providesEventsAdapter(Context context, PrettyTime prettyTime){
        return new EventsAdapter(context, prettyTime);
    }
}
