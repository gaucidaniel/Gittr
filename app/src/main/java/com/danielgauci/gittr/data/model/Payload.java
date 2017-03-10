package com.danielgauci.gittr.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daniel on 2/27/17.
 */

public class Payload implements Parcelable {


    private String action;
    private Issue issue;
    private Comment comment;
    private Release release;
    private User member;
    private List<Commit> commits;
    @SerializedName("pull_request")
    private PullRequest pullRequest;
    @SerializedName("ref_type")
    private String refType;

    public String getAction() {
        return action;
    }

    public String getRefType() {
        return refType;
    }

    public Issue getIssue() {
        return issue;
    }

    public Comment getComment() {
        return comment;
    }

    public User getMember() {
        return member;
    }

    public List<Commit> getCommits() {
        return commits;
    }

    public PullRequest getPullRequest() {
        return pullRequest;
    }

    public Release getRelease() {
        return release;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.action);
        dest.writeParcelable(this.issue, flags);
        dest.writeParcelable(this.comment, flags);
        dest.writeParcelable(this.release, flags);
        dest.writeParcelable(this.member, flags);
        dest.writeList(this.commits);
        dest.writeParcelable(this.pullRequest, flags);
        dest.writeString(this.refType);
    }

    public Payload() {
    }

    protected Payload(Parcel in) {
        this.action = in.readString();
        this.issue = in.readParcelable(Issue.class.getClassLoader());
        this.comment = in.readParcelable(Comment.class.getClassLoader());
        this.release = in.readParcelable(Release.class.getClassLoader());
        this.member = in.readParcelable(User.class.getClassLoader());
        this.commits = new ArrayList<Commit>();
        in.readList(this.commits, Commit.class.getClassLoader());
        this.pullRequest = in.readParcelable(PullRequest.class.getClassLoader());
        this.refType = in.readString();
    }

    public static final Parcelable.Creator<Payload> CREATOR = new Parcelable.Creator<Payload>() {
        @Override
        public Payload createFromParcel(Parcel source) {
            return new Payload(source);
        }

        @Override
        public Payload[] newArray(int size) {
            return new Payload[size];
        }
    };
}
