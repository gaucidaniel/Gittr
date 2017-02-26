package com.danielgauci.gittr.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by daniel on 2/24/17.
 */

public class Actor {

    @SerializedName("id")
    private Integer id;
    @SerializedName("login")
    private String login;
    @SerializedName("gravatar_id")
    private String gravatarId;
    @SerializedName("avatar_url")
    private String avatarUrl;
    @SerializedName("url")
    private String url;

    public Actor() {

    }

    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getGravatarId() {
        return gravatarId;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getUrl() {
        return url;
    }
}
