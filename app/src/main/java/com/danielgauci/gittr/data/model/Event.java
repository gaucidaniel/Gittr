package com.danielgauci.gittr.data.model;

/**
 * Created by daniel on 2/24/17.
 */

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import com.danielgauci.gittr.R;
import com.google.gson.annotations.SerializedName;

public class Event {

    @SerializedName("id")
    private String id;
    @SerializedName("type")
    private String type;
    @SerializedName("public")
    private Boolean isPublic;
    @SerializedName("payload")
    private Object payload;
    @SerializedName("repo")
    private Repo repo;
    @SerializedName("actor")
    private Actor actor;
    @SerializedName("org")
    private Org org;
    @SerializedName("created_at")
    private String createdAt;

    public Event() {

    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public Object getPayload() {
        return payload;
    }

    public Repo getRepo() {
        return repo;
    }

    public Actor getActor() {
        return actor;
    }

    public Org getOrg() {
        return org;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public SpannableStringBuilder getDescriptionSpannable(Context context) {
        // Build description string
        SpannableStringBuilder descriptionBuilder = new SpannableStringBuilder();
        descriptionBuilder.append(actor.getLogin());
        descriptionBuilder.setSpan(new ForegroundColorSpan(
                        ContextCompat.getColor(context, R.color.colorPrimary)),
                        0,
                        descriptionBuilder.length(),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        descriptionBuilder.append(" ");

        // Append body
        String repoExtra = null;
        switch (getType()) {
            case EventType.CREATE:
                descriptionBuilder.append("created a new repository");
                break;

            default:
                descriptionBuilder.append("worked on");
                break;
        }

        // Append repo name
        int spanStart = descriptionBuilder.length();
        descriptionBuilder.append(" ");
        descriptionBuilder.append(repo.getName());
        if (repoExtra != null) {
            descriptionBuilder.append(repoExtra);
        }

        descriptionBuilder.setSpan(new ForegroundColorSpan(
                        ContextCompat.getColor(context, R.color.colorPrimary)),
                        spanStart,
                        descriptionBuilder.length(),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return descriptionBuilder;
    }

    public String getMessage() {
        return "";
    }

    public static class EventType {

        private final static String CREATE = "CreateEvent";
        private final static String DELETE = "DeleteEvent";
        private final static String DEPLOYMENT = "DeploymentEvent";
        private final static String DEPLOYMENT_STATUS = "DeploymentStatusEvent";
        private final static String DOWNLOAD = "DownloadEvent";
        private final static String FOLLOW = "FollowEvent";
        private final static String FORK = "ForkEvent";
        private final static String FORK_APPLY = "ForkApplyEvent";
        private final static String GIST = "GistEvent";
        private final static String ISSUE_COMMENT = "CreateEvent";
        private final static String ISSUES = "IssuesEvent";
        private final static String LABEL = "LabelEvent";
        private final static String MEMBER = "MemberEvent";
        private final static String MEMBERSHIP = "MembershipEvent";
        private final static String MILESTONE = "MilestoneEvent";
        private final static String ORGRANIZATION = "OrganizationEvent";
        private final static String ORG_BLOCK = "OrgBlockEvent";
        private final static String PAGE_BUILD = "PageBuildEvent";
        private final static String PROJECT_CARD = "ProjectCardEvent";
        private final static String PROJECT_COLUMN = "ProjectColumnEvent";
        private final static String PROJECT = "ProjectEvent";
        private final static String PUBLIC = "PublicEvent";
        private final static String PULL_REQUEST = "PullRequestEvent";
        private final static String REQUEST_REVIEW = "PullRequestReviewEvent";
        private final static String PULL_REQUREST_REVIEW_COMMENT = "PullRequestReviewCommentEvent";
        private final static String PUSH = "PushEvent";
        private final static String RELEASE = "ReleaseEvent";
        private final static String REPOSITORY = "RepositoryEvent";
        private final static String STATUS = "StatusEvent";
        private final static String TEAM = "TeamEvent";
        private final static String TEAM_ADD = "TeamAddEvent";
        private final static String WATCH = "WatchEvent";
    }
}