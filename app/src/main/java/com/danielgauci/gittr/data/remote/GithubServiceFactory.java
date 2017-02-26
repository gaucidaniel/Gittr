package com.danielgauci.gittr.data.remote;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.Converter.Factory.*;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by daniel on 2/25/17.
 */

public class GithubServiceFactory {

    public static GithubService makeGithubService(){
        OkHttpClient okHttpClient = makeOkHttpClient();
        return makeGithubService(okHttpClient);
    }

    public static GithubService makeGithubService(OkHttpClient okHttpClient){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit.create(GithubService.class);
    }

    private static OkHttpClient makeOkHttpClient(){
        return new OkHttpClient.Builder()
                .build();
    }
}
