package com.danielgauci.gittr.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daniel on 2/27/17.
 */

public class Issue implements Parcelable {
    private String url;
    private String htmlUrl;
    private Integer id;
    private Integer number;
    private String title;
    private User user;
    private String state;
    private Boolean locked;
    private User assignee;
    private Integer comments;
    private String createdAt;
    private String updatedAt;
    private PullRequest pullRequest;
    private String body;
    private List<User> assignees = null;
    private List<Label> labels = null;
    @SerializedName("repository_url")
    private String repositoryUrl;
    @SerializedName("labels_url")
    private String labelsUrl;
    @SerializedName("comments_url")
    private String commentsUrl;
    @SerializedName("events_url")
    private String eventsUrl;
    @SerializedName("html_url")

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRepositoryUrl() {
        return repositoryUrl;
    }

    public void setRepositoryUrl(String repositoryUrl) {
        this.repositoryUrl = repositoryUrl;
    }

    public String getLabelsUrl() {
        return labelsUrl;
    }

    public void setLabelsUrl(String labelsUrl) {
        this.labelsUrl = labelsUrl;
    }

    public String getCommentsUrl() {
        return commentsUrl;
    }

    public void setCommentsUrl(String commentsUrl) {
        this.commentsUrl = commentsUrl;
    }

    public String getEventsUrl() {
        return eventsUrl;
    }

    public void setEventsUrl(String eventsUrl) {
        this.eventsUrl = eventsUrl;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
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

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
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

    public Object getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    public List<User> getAssignees() {
        return assignees;
    }

    public void setAssignees(List<User> assignees) {
        this.assignees = assignees;
    }

    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }


    public PullRequest getPullRequest() {
        return pullRequest;
    }

    public void setPullRequest(PullRequest pullRequest) {
        this.pullRequest = pullRequest;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeString(this.htmlUrl);
        dest.writeValue(this.id);
        dest.writeValue(this.number);
        dest.writeString(this.title);
        dest.writeParcelable(this.user, flags);
        dest.writeString(this.state);
        dest.writeValue(this.locked);
        dest.writeParcelable(this.assignee, flags);
        dest.writeValue(this.comments);
        dest.writeString(this.createdAt);
        dest.writeString(this.updatedAt);
        dest.writeParcelable(this.pullRequest, flags);
        dest.writeString(this.body);
        dest.writeList(this.assignees);
        dest.writeList(this.labels);
        dest.writeString(this.repositoryUrl);
        dest.writeString(this.labelsUrl);
        dest.writeString(this.commentsUrl);
        dest.writeString(this.eventsUrl);
    }

    public Issue() {
    }

    protected Issue(Parcel in) {
        this.url = in.readString();
        this.htmlUrl = in.readString();
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.number = (Integer) in.readValue(Integer.class.getClassLoader());
        this.title = in.readString();
        this.user = in.readParcelable(User.class.getClassLoader());
        this.state = in.readString();
        this.locked = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.assignee = in.readParcelable(Object.class.getClassLoader());
        this.comments = (Integer) in.readValue(Integer.class.getClassLoader());
        this.createdAt = in.readString();
        this.updatedAt = in.readString();
        this.pullRequest = in.readParcelable(PullRequest.class.getClassLoader());
        this.body = in.readString();
        this.assignees = new ArrayList<User>();
        in.readList(this.assignees, User.class.getClassLoader());
        this.labels = new ArrayList<Label>();
        in.readList(this.labels, Label.class.getClassLoader());
        this.repositoryUrl = in.readString();
        this.labelsUrl = in.readString();
        this.commentsUrl = in.readString();
        this.eventsUrl = in.readString();
    }

    public static final Parcelable.Creator<Issue> CREATOR = new Parcelable.Creator<Issue>() {
        @Override
        public Issue createFromParcel(Parcel source) {
            return new Issue(source);
        }

        @Override
        public Issue[] newArray(int size) {
            return new Issue[size];
        }
    };
}
