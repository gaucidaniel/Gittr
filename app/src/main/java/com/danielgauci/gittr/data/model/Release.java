package com.danielgauci.gittr.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daniel on 2/27/17.
 */

public class Release implements Parcelable {

    public String url;

    public String name;
    public Boolean draft;
    public User author;
    public Boolean prerelease;
    public List<Object> assets = null;
    public String body;
    @SerializedName("tarball_url")
    public String tarballUrl;
    @SerializedName("zipball_url")
    public String zipballUrl;
    @SerializedName("created_at")
    public String createdAt;
    @SerializedName("published_at")
    public String publishedAt;
    @SerializedName("assets_url")
    public String assetsUrl;
    @SerializedName("upload_url")
    public String uploadUrl;
    @SerializedName("html_url")
    public String htmlUrl;
    public Integer id;
    @SerializedName("tag_name")
    public String tagName;
    @SerializedName("target_commitish")
    public String targetCommitish;

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public Boolean getDraft() {
        return draft;
    }

    public User getAuthor() {
        return author;
    }

    public Boolean getPrerelease() {
        return prerelease;
    }

    public List<Object> getAssets() {
        return assets;
    }

    public Object getBody() {
        return body;
    }

    public String getTarballUrl() {
        return tarballUrl;
    }

    public String getZipballUrl() {
        return zipballUrl;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getAssetsUrl() {
        return assetsUrl;
    }

    public String getUploadUrl() {
        return uploadUrl;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public Integer getId() {
        return id;
    }

    public String getTagName() {
        return tagName;
    }

    public String getTargetCommitish() {
        return targetCommitish;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeString(this.name);
        dest.writeValue(this.draft);
        dest.writeParcelable(this.author, flags);
        dest.writeValue(this.prerelease);
        dest.writeList(this.assets);
        dest.writeString(this.body);
        dest.writeString(this.tarballUrl);
        dest.writeString(this.zipballUrl);
        dest.writeString(this.createdAt);
        dest.writeString(this.publishedAt);
        dest.writeString(this.assetsUrl);
        dest.writeString(this.uploadUrl);
        dest.writeString(this.htmlUrl);
        dest.writeValue(this.id);
        dest.writeString(this.tagName);
        dest.writeString(this.targetCommitish);
    }

    public Release() {
    }

    protected Release(Parcel in) {
        this.url = in.readString();
        this.name = in.readString();
        this.draft = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.author = in.readParcelable(User.class.getClassLoader());
        this.prerelease = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.assets = new ArrayList<Object>();
        in.readList(this.assets, Object.class.getClassLoader());
        this.body = in.readString();
        this.tarballUrl = in.readString();
        this.zipballUrl = in.readString();
        this.createdAt = in.readString();
        this.publishedAt = in.readString();
        this.assetsUrl = in.readString();
        this.uploadUrl = in.readString();
        this.htmlUrl = in.readString();
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.tagName = in.readString();
        this.targetCommitish = in.readString();
    }

    public static final Creator<Release> CREATOR = new Creator<Release>() {
        @Override
        public Release createFromParcel(Parcel source) {
            return new Release(source);
        }

        @Override
        public Release[] newArray(int size) {
            return new Release[size];
        }
    };
}

