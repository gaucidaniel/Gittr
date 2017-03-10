package com.danielgauci.gittr.ui.feeddetail;

import android.text.SpannableStringBuilder;

import com.danielgauci.gittr.ui.base.MvpView;

import java.util.Date;

/**
 * Created by daniel on 3/10/17.
 */

public interface FeedDetailMvpView extends MvpView {

    void setEventDescription(SpannableStringBuilder description);

    void setEventMessage(String message);

    void setEventDate(String dateString);

    void setEventProfilePicture(String profilePictureUrl);

    void setRepoTitle(String title);

    void setRepoDescription(String description);

    void setRepoLanguage(String language);

    void setRepoStarCount(int starCount);

    void setRepoForkCount(int forkCount);

    void setWatchCount(int watchCount);

    void setRepoReadme(String readme);

    void showRepoDetails(boolean show);

    void showProgress(boolean show);

    void showMessage(String message);

    void hideMessage();
}
