package com.danielgauci.gittr.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by daniel on 2/27/17.
 */

public class Payload {

    private String action;
    private Issue issue;
    private List<Commit> commits;
    @SerializedName("pull_request")
    private PullRequest pullRequest;
}
