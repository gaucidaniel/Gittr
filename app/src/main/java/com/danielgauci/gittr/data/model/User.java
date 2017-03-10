package com.danielgauci.gittr.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by daniel on 2/24/17.
 */

public class User implements Parcelable {

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.login);
        dest.writeString(this.url);
        dest.writeString(this.gravatarId);
        dest.writeString(this.avatarUrl);
    }

    protected User(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.login = in.readString();
        this.url = in.readString();
        this.gravatarId = in.readString();
        this.avatarUrl = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
