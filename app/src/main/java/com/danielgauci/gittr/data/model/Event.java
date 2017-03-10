package com.danielgauci.gittr.data.model;

/**
 * Created by daniel on 2/24/17.
 */

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import com.danielgauci.gittr.R;
import com.google.gson.annotations.SerializedName;

public class Event implements Parcelable {

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

    public SpannableStringBuilder getDescriptionSpannable(Context context, int color) {
        // Build description string
        SpannableStringBuilder descriptionBuilder = new SpannableStringBuilder();
        descriptionBuilder.append(actor.getLogin());
        descriptionBuilder.setSpan(new ForegroundColorSpan(
                        ContextCompat.getColor(context, color)),
                0,
                descriptionBuilder.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        descriptionBuilder.append(" ");

        // Append body
        String repoExtra = null;
        switch (getType()) {
            case EventType.CREATE:
                // New repository created
                descriptionBuilder.append("created ");
                descriptionBuilder.append(payload.getRefType());
                descriptionBuilder.append(" in");
                break;

            case EventType.DELETE:
                // Repository deleted
                descriptionBuilder.append("deleted ");
                descriptionBuilder.append(payload.getRefType());
                descriptionBuilder.append(" in");
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
                        new ForegroundColorSpan(ContextCompat.getColor(context, color)),
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
                        ContextCompat.getColor(context, color)),
                spanStart,
                descriptionBuilder.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return descriptionBuilder;
    }

    public String getMessage() {
        String quotationMark = "\"";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(quotationMark);

        // Build message based on event type
        switch (getType()) {
            case EventType.ISSUE_COMMENT:
                // New comment issued on repository
                stringBuilder.append(payload.getComment().getBody());
                break;

            case EventType.ISSUES:
                // Issue was affected on a repository
                stringBuilder.append(payload.getIssue().getTitle());
                break;

            case EventType.PULL_REQUEST_REVIEW:
            case EventType.PULL_REQUEST:
                stringBuilder.append(payload.getPullRequest().getTitle());
                break;

            case EventType.PULL_REQUREST_REVIEW_COMMENT:
                stringBuilder.append(payload.getComment().getBody());
                break;

            case EventType.PUSH:
                if (!payload.getCommits().isEmpty()) {
                    stringBuilder.append(payload.getCommits().get(0).getMessage());
                }
                break;

            case EventType.RELEASE:
                stringBuilder.append(payload.getRelease().tagName);
                break;
        }

        // Return empty string if no message is available
        if (stringBuilder.toString().equals(quotationMark)){
            return "";
        }

        stringBuilder.append("\"");
        return stringBuilder.toString();
    }

    public static class EventType {

        public final static String CREATE = "CreateEvent";
        public final static String DELETE = "DeleteEvent";
        public final static String FORK = "ForkEvent";
        public final static String ISSUE_COMMENT = "IssueCommentEvent";
        public final static String ISSUES = "IssuesEvent";
        public final static String MEMBER = "MemberEvent";
        public final static String PUBLIC = "PublicEvent";
        public final static String PULL_REQUEST = "PullRequestEvent";
        public final static String PULL_REQUEST_REVIEW = "PullRequestReviewEvent";
        public final static String PULL_REQUREST_REVIEW_COMMENT = "PullRequestReviewCommentEvent";
        public final static String PUSH = "PushEvent";
        public final static String RELEASE = "ReleaseEvent";
        public final static String REPOSITORY = "RepositoryEvent";
        public final static String WATCH = "WatchEvent";
    }

    protected Event(Parcel in) {
        id = in.readString();
        type = in.readString();
        payload = (Payload) in.readValue(Payload.class.getClassLoader());
        repo = (Repo) in.readValue(Repo.class.getClassLoader());
        actor = (User) in.readValue(User.class.getClassLoader());
        org = (Org) in.readValue(Org.class.getClassLoader());
        createdAt = in.readString();
        byte isPublicVal = in.readByte();
        isPublic = isPublicVal == 0x02 ? null : isPublicVal != 0x00;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(type);
        dest.writeValue(payload);
        dest.writeValue(repo);
        dest.writeValue(actor);
        dest.writeValue(org);
        dest.writeString(createdAt);
        if (isPublic == null) {
            dest.writeByte((byte) (0x02));
        } else {
            dest.writeByte((byte) (isPublic ? 0x01 : 0x00));
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Event> CREATOR = new Parcelable.Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };
}