package com.danielgauci.gittr.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by daniel on 2/25/17.
 */

public class Org {

    private Integer id;
    private String login;
    private String url;
    @SerializedName("avatar_url")
    private String avatarUrl;
    @SerializedName("gravatar_id")
    private String gravatarId;

    public Org() {
    }
}
