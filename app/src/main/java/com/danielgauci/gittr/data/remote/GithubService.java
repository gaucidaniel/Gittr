package com.danielgauci.gittr.data.remote;

import com.danielgauci.gittr.data.model.Event;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by daniel on 2/25/17.
 */

public interface GithubService {

    @GET("events")
    Observable<List<Event>> getPublicEvents();

    @GET("users/{username}/received_events")
    Observable<List<Event>> getReceivedEvents(@Path("username") String username);

}
