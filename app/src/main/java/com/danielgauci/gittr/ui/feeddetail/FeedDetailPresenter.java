package com.danielgauci.gittr.ui.feeddetail;

import android.content.Context;

import com.danielgauci.gittr.R;
import com.danielgauci.gittr.data.DataManager;
import com.danielgauci.gittr.data.model.Event;
import com.danielgauci.gittr.ui.base.BasePresenter;

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
    private Disposable mDisposable;

    @Inject
    public FeedDetailPresenter(DataManager mDataManager, Context mContext) {
        this.mDataManager = mDataManager;
        this.mContext = mContext;
    }

    public void setEvent(Event event){
        // Set basic event data
        getMvpView().setEventDescription(event.getDescriptionSpannable(mContext, android.R.color.white));
        getMvpView().setEventMessage(event.getMessage());
        getMvpView().setRepoTitle(event.getRepo().getName());

        // Get repository details
        getRepository(event.getRepo().getName());
    }

    private void getRepository(String name){
        // Show progress wheel
        getMvpView().showProgress(true);
        getMvpView().showRepoDetails(false);
        getMvpView().hideMessage();

        // Subscribe to observable
        mDisposable = mDataManager.getRepository(name)
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
                    getMvpView().setRepoReadme("https://raw.githubusercontent.com/"
                            + repo.getFullName()
                            + "/master/README.md");

                    // Show repo details
                    getMvpView().hideMessage();
                    getMvpView().showProgress(false);
                    getMvpView().showRepoDetails(true);

                }, (error) -> {
                    // Show error
                    getMvpView().showProgress(false);
                    getMvpView().showRepoDetails(false);
                    getMvpView().showMessage(error.getMessage());

                    // Log error
                    Timber.e(error, error.getMessage());
                });
    }

    @Override
    public void detachView() {
        super.detachView();

        // Dispose of subscription on detach
        if (mDisposable != null){
            mDisposable.dispose();
        }
    }
}
