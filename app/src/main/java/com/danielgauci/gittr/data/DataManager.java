package com.danielgauci.gittr.data;

import com.danielgauci.gittr.data.model.Event;
import com.danielgauci.gittr.data.remote.GithubService;
import com.danielgauci.gittr.data.remote.GithubServiceFactory;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by daniel on 2/25/17.
 */

public class DataManager {

    private GithubService mGithubService;

    @Inject
    public DataManager(GithubService mGithubService) {
        this.mGithubService = mGithubService;
    }

    public Observable<List<Event>> getPublicEvents(int page){
        return mGithubService.getPublicEvents(page);
    }

    public Observable<List<Event>> getUserEvents(String username, int page){
        return mGithubService.getUserEvents(username, page);
    }
}
