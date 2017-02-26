package com.danielgauci.gittr.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by daniel on 2/24/17.
 */

public class Repo {

    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;
    @SerializedName("url")
    private String url;

    public Repo() {

    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}

