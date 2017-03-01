package com.danielgauci.gittr.injection.module;

import com.danielgauci.gittr.data.remote.GithubService;
import com.danielgauci.gittr.data.remote.GithubServiceFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by daniel on 3/1/17.
 */

@Module
public class NetworkModule {

    public NetworkModule() {

    }

    @Provides
    @Singleton
    private GithubService provideGithubService(){
        return GithubServiceFactory.makeGithubService();
    }
}
