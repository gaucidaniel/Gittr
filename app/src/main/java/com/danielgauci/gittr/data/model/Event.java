package com.danielgauci.gittr.data.model;

/**
 * Created by daniel on 2/24/17.
 */

import com.google.gson.annotations.SerializedName;

public class Event {

    @SerializedName("id")
    private String id;
    @SerializedName("type")
    private String type;
    @SerializedName("public")
    private Boolean isPublic;
    @SerializedName("payload")
    private Object payload;
    @SerializedName("repo")
    private Repo repo;
    @SerializedName("actor")
    private Actor actor;
    @SerializedName("org")
    private Org org;
    @SerializedName("created_at")
    private String createdAt;

    public Event() {

    }
}