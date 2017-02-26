package com.danielgauci.gittr.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by daniel on 2/25/17.
 */

public class Org {

    @SerializedName("id")
    private Integer id;
    @SerializedName("login")
    private String login;
    @SerializedName("gravatar_id")
    private String gravatarId;
    @SerializedName("url")
    private String url;
    @SerializedName("avatar_url")
    private String avatarUrl;

    public Org() {
    }
}
