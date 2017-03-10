package com.danielgauci.gittr.injection.module;

import android.content.Context;

import com.danielgauci.gittr.data.DataManager;
import com.danielgauci.gittr.injection.ViewScope;
import com.danielgauci.gittr.ui.common.EventsAdapter;
import com.danielgauci.gittr.ui.feed.FeedPresenter;
import com.danielgauci.gittr.ui.feed.FiltersAdapter;
import com.danielgauci.gittr.ui.feeddetail.FeedDetailPresenter;
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
    public FeedPresenter providesFeedPresenter(DataManager dataManager, Context context){
        return new FeedPresenter(dataManager, context);
    }

    @Provides
    @Singleton
    public SearchPresenter providesSearchPresenter(DataManager dataManager, Context context){
        return new SearchPresenter(dataManager, context);
    }

    @Provides
    FeedDetailPresenter providesDetailPresenter(DataManager dataManager, Context context){
        return new FeedDetailPresenter(dataManager, context);
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

    @Provides
    public FiltersAdapter providesFiltersAdapter(Context context){
        return new FiltersAdapter(context);
    }
}
