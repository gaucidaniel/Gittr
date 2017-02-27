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

    private String id;
    private String type;
    private Payload payload;
    private Repo repo;
    private User actor;
    private Org org;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("public")
    private Boolean isPublic;

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

    public Payload getPayload() {
        return payload;
    }

    public Repo getRepo() {
        return repo;
    }

    public User getActor() {
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
                // New repository created
                descriptionBuilder.append("created a new ");
                descriptionBuilder.append(payload.getRefType());
                descriptionBuilder.append(" in");
                break;

            case EventType.DELETE:
                // Repository deleted
                descriptionBuilder.append("deleted repository");
                break;

            case EventType.FORK:
                // Repository forked
                descriptionBuilder.append("forked");
                break;

            case EventType.ISSUE_COMMENT:
                // New comment issued on repository
                descriptionBuilder.append("commented on");
                repoExtra = "#" + payload.getIssue().getNumber();
                break;

            case EventType.ISSUES:
                // Issue was affected on a repository
                descriptionBuilder.append(payload.getAction());
                descriptionBuilder.append(" issue");
                repoExtra = "#" + payload.getIssue().getNumber();
                break;

            case EventType.MEMBER:
                // Collaborator change was made on a repository
                descriptionBuilder.append(payload.getAction());
                descriptionBuilder.append(" ");

                int memberStartPosition =  descriptionBuilder.length();
                descriptionBuilder.append(payload.getMember().getLogin());
                descriptionBuilder.setSpan(
                        new ForegroundColorSpan(ContextCompat.getColor(context, R.color.colorAccent)),
                        memberStartPosition,
                        descriptionBuilder.length(),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                descriptionBuilder.append(" in");
                break;

            case EventType.PUBLIC:
                descriptionBuilder.append("has seen the light and open sourced");
                break;

            case EventType.PULL_REQUEST_REVIEW:
            case EventType.PULL_REQUEST:
                descriptionBuilder.append(payload.getAction());
                descriptionBuilder.append(" pull request");
                repoExtra = "#" + payload.getPullRequest().getNumber();
                break;

            case EventType.PULL_REQUREST_REVIEW_COMMENT:
                descriptionBuilder.append(payload.getAction());
                descriptionBuilder.append(" comment on");
                repoExtra = "#" + payload.getPullRequest().getNumber();
                break;

            case EventType.PUSH:
                descriptionBuilder.append("pushed to");
                break;

            case EventType.RELEASE:
                descriptionBuilder.append("published a release on");
                break;

            case EventType.REPOSITORY:
                descriptionBuilder.append(payload.getAction());
                break;

            case EventType.WATCH:
                descriptionBuilder.append("starred");
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
        private final static String FORK = "ForkEvent";
        private final static String ISSUE_COMMENT = "IssueCommentEvent";
        private final static String ISSUES = "IssuesEvent";
        private final static String MEMBER = "MemberEvent";
        private final static String PUBLIC = "PublicEvent";
        private final static String PULL_REQUEST = "PullRequestEvent";
        private final static String PULL_REQUEST_REVIEW = "PullRequestReviewEvent";
        private final static String PULL_REQUREST_REVIEW_COMMENT = "PullRequestReviewCommentEvent";
        private final static String PUSH = "PushEvent";
        private final static String RELEASE = "ReleaseEvent";
        private final static String REPOSITORY = "RepositoryEvent";
        private final static String WATCH = "WatchEvent";
    }
}