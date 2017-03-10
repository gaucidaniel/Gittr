package com.danielgauci.gittr.ui.feeddetail;

import android.content.Context;

import com.danielgauci.gittr.R;
import com.danielgauci.gittr.data.DataManager;
import com.danielgauci.gittr.data.model.Event;
import com.danielgauci.gittr.ui.base.BasePresenter;
import com.danielgauci.gittr.utils.DateUtils;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by daniel on 3/10/17.
 */

public class FeedDetailPresenter extends BasePresenter<FeedDetailMvpView> {

    private DataManager mDataManager;
    private Context mContext;
    private PrettyTime mPrettyTime;
    private Disposable mDisposable;

    @Inject
    public FeedDetailPresenter(DataManager dataManager, PrettyTime prettyTime, Context context) {
        this.mDataManager = dataManager;
        this.mPrettyTime = prettyTime;
        this.mContext = context;
    }

    public void setEvent(Event event) {
        // Set basic event data
        getMvpView().setEventDescription(event.getDescriptionSpannable(mContext, android.R.color.white));
        getMvpView().setEventMessage(event.getMessage());
        getMvpView().setRepoTitle(event.getRepo().getName());
        getMvpView().setEventProfilePicture(event.getActor().getAvatarUrl());
        getMvpView().setEventDate(DateUtils.formatDate(event.getCreatedAt(), mPrettyTime));

        // Get repository details
        String username = event.getRepo().getName().split("/")[0];
        String repo = event.getRepo().getName().split("/")[1];
        getRepository(username, repo);
    }

    private void getRepository(String username, String repoName) {
        // Show progress wheel
        getMvpView().showProgress(true);
        getMvpView().showRepoDetails(false);
        getMvpView().hideMessage();

        // Subscribe to observable
        mDisposable = mDataManager.getRepository(username, repoName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe((repo) -> {
                    // Set repo data
                    getMvpView().setRepoDescription(repo.getDescription() != null ?
                            repo.getDescription() : mContext.getString(R.string.repository_no_description));
                    getMvpView().setRepoLanguage(repo.getLanguage());
                    getMvpView().setRepoForkCount(repo.getForksCount());
                    getMvpView().setWatchCount(repo.getWatchersCount());
                    getMvpView().setRepoStarCount(repo.getStargazersCount());

                    // Get repo readme
                    Ion.with(mContext)
                            .load("https://raw.githubusercontent.com/" + repo.getFullName() + "/master/README.md")
                            .asString()
                            .setCallback((Exception e, String result) -> {
                                getMvpView().setRepoReadme(e == null ? result : e.getMessage());
                            });


                    // Show repo details
                    getMvpView().hideMessage();
                    getMvpView().showProgress(false);
                    getMvpView().showRepoDetails(true);

                }, (error) -> {
                    // Show error
                    getMvpView().showProgress(false);
                    getMvpView().showRepoDetails(false);
                    getMvpView().showMessage("Failed to load repository details.");

                    // Log error
                    Timber.e(error, error.getMessage());
                });
    }

    @Override
    public void detachView() {
        super.detachView();

        // Dispose of subscription on detach
        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }
}
