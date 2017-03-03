package com.danielgauci.gittr.injection.module;

import com.danielgauci.gittr.data.DataManager;
import com.danielgauci.gittr.data.remote.GithubService;
import com.danielgauci.gittr.data.remote.GithubServiceFactory;
import com.danielgauci.gittr.injection.DataScope;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by daniel on 3/1/17.
 */

@Module
public class DataModule {

    @Provides
    @Singleton
    public GithubService provideGithubService(){
        return GithubServiceFactory.makeGithubService();
    }

    @Provides
    @Singleton
    public DataManager provideDataManager(GithubService githubService){
        return new DataManager(githubService);
    }
}
