package com.danielgauci.gittr.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by daniel on 2/27/17.
 */

public class Payload {

    private String action;
    private Issue issue;
    private Comment comment;
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
}
