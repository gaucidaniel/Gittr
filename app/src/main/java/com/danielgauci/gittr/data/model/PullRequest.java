package com.danielgauci.gittr.data.model;

/**
 * Created by daniel on 2/27/17.
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class PullRequest implements Parcelable {

    private String url;
    private Integer id;
    private String issueUrl;
    private Integer number;
    private String state;
    private Boolean locked;
    private String title;
    private User user;
    @SerializedName("html_url")
    private String htmlUrl;
    @SerializedName("diff_url")
    private String diffUrl;
    @SerializedName("patch_url")
    private String patchUrl;
    @SerializedName("issue_url")

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getDiffUrl() {
        return diffUrl;
    }

    public void setDiffUrl(String diffUrl) {
        this.diffUrl = diffUrl;
    }

    public String getPatchUrl() {
        return patchUrl;
    }

    public void setPatchUrl(String patchUrl) {
        this.patchUrl = patchUrl;
    }

    public String getIssueUrl() {
        return issueUrl;
    }

    public void setIssueUrl(String issueUrl) {
        this.issueUrl = issueUrl;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeValue(this.id);
        dest.writeString(this.issueUrl);
        dest.writeValue(this.number);
        dest.writeString(this.state);
        dest.writeValue(this.locked);
        dest.writeString(this.title);
        dest.writeParcelable(this.user, flags);
        dest.writeString(this.htmlUrl);
        dest.writeString(this.diffUrl);
        dest.writeString(this.patchUrl);
    }

    public PullRequest() {
    }

    protected PullRequest(Parcel in) {
        this.url = in.readString();
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.issueUrl = in.readString();
        this.number = (Integer) in.readValue(Integer.class.getClassLoader());
        this.state = in.readString();
        this.locked = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.title = in.readString();
        this.user = in.readParcelable(User.class.getClassLoader());
        this.htmlUrl = in.readString();
        this.diffUrl = in.readString();
        this.patchUrl = in.readString();
    }

    public static final Parcelable.Creator<PullRequest> CREATOR = new Parcelable.Creator<PullRequest>() {
        @Override
        public PullRequest createFromParcel(Parcel source) {
            return new PullRequest(source);
        }

        @Override
        public PullRequest[] newArray(int size) {
            return new PullRequest[size];
        }
    };
}