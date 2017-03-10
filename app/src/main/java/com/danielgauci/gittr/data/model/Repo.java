package com.danielgauci.gittr.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by daniel on 2/24/17.
 */

public class Repo implements Parcelable {

    private Integer id;
    private String name;
    @SerializedName("full_name")
    private String fullName;
    private User owner;
    @SerializedName("private")
    private Boolean _private;
    @SerializedName("html_url")
    private String htmlUrl;
    private String description;
    private Boolean fork;
    private String url;
    @SerializedName("forks_url")
    private String forksUrl;
    @SerializedName("keys_url")
    private String keysUrl;
    @SerializedName("collaborators_url")
    private String collaboratorsUrl;
    @SerializedName("teams_url")
    private String teamsUrl;
    @SerializedName("hooks_url")
    private String hooksUrl;
    @SerializedName("issue_events_url")
    private String issueEventsUrl;
    @SerializedName("events_url")
    private String eventsUrl;
    @SerializedName("assignees_url")
    private String assigneesUrl;
    @SerializedName("branches_url")
    private String branchesUrl;
    @SerializedName("tags_url")
    private String tagsUrl;
    @SerializedName("blobs_url")
    private String blobsUrl;
    @SerializedName("git_tags_url")
    private String gitTagsUrl;
    @SerializedName("git_refs_url")
    private String gitRefsUrl;
    @SerializedName("trees_url")
    private String treesUrl;
    @SerializedName("statuses_url")
    private String statusesUrl;
    @SerializedName("languages_url")
    private String languagesUrl;
    @SerializedName("stargazers_url")
    private String stargazersUrl;
    @SerializedName("contributors_url")
    private String contributorsUrl;
    @SerializedName("subscribers_url")
    private String subscribersUrl;
    @SerializedName("subscription_url")
    private String subscriptionUrl;
    @SerializedName("commits_url")
    private String commitsUrl;
    @SerializedName("git_commits_url")
    private String gitCommitsUrl;
    @SerializedName("comments_url")
    private String commentsUrl;
    @SerializedName("issue_comment_url")
    private String issueCommentUrl;
    @SerializedName("contents_url")
    private String contentsUrl;
    @SerializedName("compare_url")
    private String compareUrl;
    @SerializedName("merges_url")
    private String mergesUrl;
    @SerializedName("archive_url")
    private String archiveUrl;
    @SerializedName("downloads_url")
    private String downloadsUrl;
    @SerializedName("issues_url")
    private String issuesUrl;
    @SerializedName("pulls_url")
    private String pullsUrl;
    @SerializedName("milestones_url")
    private String milestonesUrl;
    @SerializedName("notifications_url")
    private String notificationsUrl;
    @SerializedName("labels_url")
    private String labelsUrl;
    @SerializedName("releases_url")
    private String releasesUrl;
    @SerializedName("deployments_url")
    private String deploymentsUrl;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("pushed_at")
    private String pushedAt;
    @SerializedName("git_url")
    private String gitUrl;
    @SerializedName("ssh_url")
    private String sshUrl;
    @SerializedName("clone_url")
    private String cloneUrl;
    @SerializedName("svn_url")
    private String svnUrl;
    private String homepage;
    private Integer size;
    @SerializedName("stargazers_count")
    private Integer stargazersCount;
    @SerializedName("watchers_count")
    private Integer watchersCount;
    private String language;
    @SerializedName("has_issues")
    private Boolean hasIssues;
    @SerializedName("has_downloads")
    private Boolean hasDownloads;
    @SerializedName("has_wiki")
    private Boolean hasWiki;
    @SerializedName("has_pages")
    private Boolean hasPages;
    @SerializedName("forks_count")
    private Integer forksCount;
    @SerializedName("mirror_url")
    private String mirrorUrl;
    @SerializedName("open_issues_count")
    private Integer openIssuesCount;
    private Integer forks;
    @SerializedName("open_issues")
    private Integer openIssues;
    private Integer watchers;
    @SerializedName("default_branch")
    private String defaultBranch;
    @SerializedName("network_count")
    private Integer networkCount;
    @SerializedName("subscribers_count")
    private Integer subscribersCount;

    public Repo() {

    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    public User getOwner() {
        return owner;
    }

    public Boolean get_private() {
        return _private;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getFork() {
        return fork;
    }

    public String getUrl() {
        return url;
    }

    public String getForksUrl() {
        return forksUrl;
    }

    public String getKeysUrl() {
        return keysUrl;
    }

    public String getCollaboratorsUrl() {
        return collaboratorsUrl;
    }

    public String getTeamsUrl() {
        return teamsUrl;
    }

    public String getHooksUrl() {
        return hooksUrl;
    }

    public String getIssueEventsUrl() {
        return issueEventsUrl;
    }

    public String getEventsUrl() {
        return eventsUrl;
    }

    public String getAssigneesUrl() {
        return assigneesUrl;
    }

    public String getBranchesUrl() {
        return branchesUrl;
    }

    public String getTagsUrl() {
        return tagsUrl;
    }

    public String getBlobsUrl() {
        return blobsUrl;
    }

    public String getGitTagsUrl() {
        return gitTagsUrl;
    }

    public String getGitRefsUrl() {
        return gitRefsUrl;
    }

    public String getTreesUrl() {
        return treesUrl;
    }

    public String getStatusesUrl() {
        return statusesUrl;
    }

    public String getLanguagesUrl() {
        return languagesUrl;
    }

    public String getStargazersUrl() {
        return stargazersUrl;
    }

    public String getContributorsUrl() {
        return contributorsUrl;
    }

    public String getSubscribersUrl() {
        return subscribersUrl;
    }

    public String getSubscriptionUrl() {
        return subscriptionUrl;
    }

    public String getCommitsUrl() {
        return commitsUrl;
    }

    public String getGitCommitsUrl() {
        return gitCommitsUrl;
    }

    public String getCommentsUrl() {
        return commentsUrl;
    }

    public String getIssueCommentUrl() {
        return issueCommentUrl;
    }

    public String getContentsUrl() {
        return contentsUrl;
    }

    public String getCompareUrl() {
        return compareUrl;
    }

    public String getMergesUrl() {
        return mergesUrl;
    }

    public String getArchiveUrl() {
        return archiveUrl;
    }

    public String getDownloadsUrl() {
        return downloadsUrl;
    }

    public String getIssuesUrl() {
        return issuesUrl;
    }

    public String getPullsUrl() {
        return pullsUrl;
    }

    public String getMilestonesUrl() {
        return milestonesUrl;
    }

    public String getNotificationsUrl() {
        return notificationsUrl;
    }

    public String getLabelsUrl() {
        return labelsUrl;
    }

    public String getReleasesUrl() {
        return releasesUrl;
    }

    public String getDeploymentsUrl() {
        return deploymentsUrl;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getPushedAt() {
        return pushedAt;
    }

    public String getGitUrl() {
        return gitUrl;
    }

    public String getSshUrl() {
        return sshUrl;
    }

    public String getCloneUrl() {
        return cloneUrl;
    }

    public String getSvnUrl() {
        return svnUrl;
    }

    public Object getHomepage() {
        return homepage;
    }

    public Integer getSize() {
        return size;
    }

    public Integer getStargazersCount() {
        return stargazersCount;
    }

    public Integer getWatchersCount() {
        return watchersCount;
    }

    public String getLanguage() {
        return language;
    }

    public Boolean getHasIssues() {
        return hasIssues;
    }

    public Boolean getHasDownloads() {
        return hasDownloads;
    }

    public Boolean getHasWiki() {
        return hasWiki;
    }

    public Boolean getHasPages() {
        return hasPages;
    }

    public Integer getForksCount() {
        return forksCount;
    }

    public Object getMirrorUrl() {
        return mirrorUrl;
    }

    public Integer getOpenIssuesCount() {
        return openIssuesCount;
    }

    public Integer getForks() {
        return forks;
    }

    public Integer getOpenIssues() {
        return openIssues;
    }

    public Integer getWatchers() {
        return watchers;
    }

    public String getDefaultBranch() {
        return defaultBranch;
    }

    public Integer getNetworkCount() {
        return networkCount;
    }

    public Integer getSubscribersCount() {
        return subscribersCount;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeString(this.fullName);
        dest.writeParcelable(this.owner, flags);
        dest.writeValue(this._private);
        dest.writeString(this.htmlUrl);
        dest.writeString(this.description);
        dest.writeValue(this.fork);
        dest.writeString(this.url);
        dest.writeString(this.forksUrl);
        dest.writeString(this.keysUrl);
        dest.writeString(this.collaboratorsUrl);
        dest.writeString(this.teamsUrl);
        dest.writeString(this.hooksUrl);
        dest.writeString(this.issueEventsUrl);
        dest.writeString(this.eventsUrl);
        dest.writeString(this.assigneesUrl);
        dest.writeString(this.branchesUrl);
        dest.writeString(this.tagsUrl);
        dest.writeString(this.blobsUrl);
        dest.writeString(this.gitTagsUrl);
        dest.writeString(this.gitRefsUrl);
        dest.writeString(this.treesUrl);
        dest.writeString(this.statusesUrl);
        dest.writeString(this.languagesUrl);
        dest.writeString(this.stargazersUrl);
        dest.writeString(this.contributorsUrl);
        dest.writeString(this.subscribersUrl);
        dest.writeString(this.subscriptionUrl);
        dest.writeString(this.commitsUrl);
        dest.writeString(this.gitCommitsUrl);
        dest.writeString(this.commentsUrl);
        dest.writeString(this.issueCommentUrl);
        dest.writeString(this.contentsUrl);
        dest.writeString(this.compareUrl);
        dest.writeString(this.mergesUrl);
        dest.writeString(this.archiveUrl);
        dest.writeString(this.downloadsUrl);
        dest.writeString(this.issuesUrl);
        dest.writeString(this.pullsUrl);
        dest.writeString(this.milestonesUrl);
        dest.writeString(this.notificationsUrl);
        dest.writeString(this.labelsUrl);
        dest.writeString(this.releasesUrl);
        dest.writeString(this.deploymentsUrl);
        dest.writeString(this.createdAt);
        dest.writeString(this.updatedAt);
        dest.writeString(this.pushedAt);
        dest.writeString(this.gitUrl);
        dest.writeString(this.sshUrl);
        dest.writeString(this.cloneUrl);
        dest.writeString(this.svnUrl);
        dest.writeString(this.homepage);
        dest.writeValue(this.size);
        dest.writeValue(this.stargazersCount);
        dest.writeValue(this.watchersCount);
        dest.writeString(this.language);
        dest.writeValue(this.hasIssues);
        dest.writeValue(this.hasDownloads);
        dest.writeValue(this.hasWiki);
        dest.writeValue(this.hasPages);
        dest.writeValue(this.forksCount);
        dest.writeString(this.mirrorUrl);
        dest.writeValue(this.openIssuesCount);
        dest.writeValue(this.forks);
        dest.writeValue(this.openIssues);
        dest.writeValue(this.watchers);
        dest.writeString(this.defaultBranch);
        dest.writeValue(this.networkCount);
        dest.writeValue(this.subscribersCount);
    }

    protected Repo(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
        this.fullName = in.readString();
        this.owner = in.readParcelable(User.class.getClassLoader());
        this._private = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.htmlUrl = in.readString();
        this.description = in.readString();
        this.fork = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.url = in.readString();
        this.forksUrl = in.readString();
        this.keysUrl = in.readString();
        this.collaboratorsUrl = in.readString();
        this.teamsUrl = in.readString();
        this.hooksUrl = in.readString();
        this.issueEventsUrl = in.readString();
        this.eventsUrl = in.readString();
        this.assigneesUrl = in.readString();
        this.branchesUrl = in.readString();
        this.tagsUrl = in.readString();
        this.blobsUrl = in.readString();
        this.gitTagsUrl = in.readString();
        this.gitRefsUrl = in.readString();
        this.treesUrl = in.readString();
        this.statusesUrl = in.readString();
        this.languagesUrl = in.readString();
        this.stargazersUrl = in.readString();
        this.contributorsUrl = in.readString();
        this.subscribersUrl = in.readString();
        this.subscriptionUrl = in.readString();
        this.commitsUrl = in.readString();
        this.gitCommitsUrl = in.readString();
        this.commentsUrl = in.readString();
        this.issueCommentUrl = in.readString();
        this.contentsUrl = in.readString();
        this.compareUrl = in.readString();
        this.mergesUrl = in.readString();
        this.archiveUrl = in.readString();
        this.downloadsUrl = in.readString();
        this.issuesUrl = in.readString();
        this.pullsUrl = in.readString();
        this.milestonesUrl = in.readString();
        this.notificationsUrl = in.readString();
        this.labelsUrl = in.readString();
        this.releasesUrl = in.readString();
        this.deploymentsUrl = in.readString();
        this.createdAt = in.readString();
        this.updatedAt = in.readString();
        this.pushedAt = in.readString();
        this.gitUrl = in.readString();
        this.sshUrl = in.readString();
        this.cloneUrl = in.readString();
        this.svnUrl = in.readString();
        this.homepage = in.readString();
        this.size = (Integer) in.readValue(Integer.class.getClassLoader());
        this.stargazersCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.watchersCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.language = in.readString();
        this.hasIssues = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.hasDownloads = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.hasWiki = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.hasPages = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.forksCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.mirrorUrl = in.readString();
        this.openIssuesCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.forks = (Integer) in.readValue(Integer.class.getClassLoader());
        this.openIssues = (Integer) in.readValue(Integer.class.getClassLoader());
        this.watchers = (Integer) in.readValue(Integer.class.getClassLoader());
        this.defaultBranch = in.readString();
        this.networkCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.subscribersCount = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Creator<Repo> CREATOR = new Creator<Repo>() {
        @Override
        public Repo createFromParcel(Parcel source) {
            return new Repo(source);
        }

        @Override
        public Repo[] newArray(int size) {
            return new Repo[size];
        }
    };
}

