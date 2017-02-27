package com.danielgauci.gittr.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by daniel on 2/24/17.
 */

public class User {

    private Integer id;
    private String login;
    private String url;
    @SerializedName("gravatar_id")
    private String gravatarId;
    @SerializedName("avatar_url")
    private String avatarUrl;

    public User() {

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
