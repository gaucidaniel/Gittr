package com.danielgauci.gittr.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by daniel on 2/27/17.
 */

public class Commit implements Parcelable {

    private String sha;
    private User author;
    private String message;
    private Boolean distinct;
    private String url;

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.sha);
        dest.writeParcelable(this.author, flags);
        dest.writeString(this.message);
        dest.writeValue(this.distinct);
        dest.writeString(this.url);
    }

    public Commit() {
    }

    protected Commit(Parcel in) {
        this.sha = in.readString();
        this.author = in.readParcelable(User.class.getClassLoader());
        this.message = in.readString();
        this.distinct = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.url = in.readString();
    }

    public static final Parcelable.Creator<Commit> CREATOR = new Parcelable.Creator<Commit>() {
        @Override
        public Commit createFromParcel(Parcel source) {
            return new Commit(source);
        }

        @Override
        public Commit[] newArray(int size) {
            return new Commit[size];
        }
    };
}
