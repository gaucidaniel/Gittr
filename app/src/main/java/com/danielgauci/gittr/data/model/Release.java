package com.danielgauci.gittr.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by daniel on 2/27/17.
 */

public class Release {

    public String url;

    public Object name;
    public Boolean draft;
    public User author;
    public Boolean prerelease;
    public List<Object> assets = null;
    public Object body;
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

    public Object getName() {
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
}

