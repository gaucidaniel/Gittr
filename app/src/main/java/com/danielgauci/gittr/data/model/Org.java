package com.danielgauci.gittr.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by daniel on 2/25/17.
 */

public class Org implements Parcelable {

    private Integer id;
    private String login;
    private String url;
    @SerializedName("avatar_url")
    private String avatarUrl;
    @SerializedName("gravatar_id")
    private String gravatarId;

    public Org() {

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
        dest.writeString(this.avatarUrl);
        dest.writeString(this.gravatarId);
    }

    protected Org(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.login = in.readString();
        this.url = in.readString();
        this.avatarUrl = in.readString();
        this.gravatarId = in.readString();
    }

    public static final Parcelable.Creator<Org> CREATOR = new Parcelable.Creator<Org>() {
        @Override
        public Org createFromParcel(Parcel source) {
            return new Org(source);
        }

        @Override
        public Org[] newArray(int size) {
            return new Org[size];
        }
    };
}
