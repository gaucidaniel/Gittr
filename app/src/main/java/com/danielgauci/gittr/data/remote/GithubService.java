package com.danielgauci.gittr.data.remote;

import com.danielgauci.gittr.data.model.Event;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by daniel on 2/25/17.
 */

public interface GithubService {

    @GET("events")
    Observable<List<Event>> getPublicEvents();


    @GET("events")
    Observable<List<Event>> getPublicEvents(@Query("page") int page);

    @GET("users/{username}/events")
    Observable<List<Event>> getUserEvents(@Path("username") String username, @Query("page") int page);

    @GET("users/{username}/received_events")
    Observable<List<Event>> getUserReceivedEvents(@Path("username") String username);
}
