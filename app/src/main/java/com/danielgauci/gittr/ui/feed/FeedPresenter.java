package com.danielgauci.gittr.ui.feed;

import android.content.Context;

import com.danielgauci.gittr.data.DataManager;
import com.danielgauci.gittr.data.model.Event;
import com.danielgauci.gittr.ui.base.BasePresenter;
import com.danielgauci.gittr.utils.NetworkUtils;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by daniel on 2/26/17.
 */

public class FeedPresenter extends BasePresenter<FeedMvpView> {

    private Context mContext;
    private DataManager mDataManager;
    private Disposable mDisposable;
    private int mPage = 0;
    private boolean mIsLoading = false;

    @Inject
    public FeedPresenter(DataManager dataManager, Context context) {
        this.mDataManager = dataManager;
        this.mContext = context;
    }

    public void getNextEvents() {
        checkViewAttached();

        // Stop if already loading
        if (mIsLoading) {
            return;
        }

        // Check network
        if (!NetworkUtils.isInternetAvailable(mContext)){
            getMvpView().showInfiniteScrollProgress(false);
            getMvpView().showProgress(false);
            getMvpView().clearEvents();
            getMvpView().showMessage("No internet connection.");
            return;
        }

        // Hide any messages and show progress wheel
        getMvpView().hideMessage();

        if (mPage == 0) {
            getMvpView().showProgress(true);
        } else {
            getMvpView().showInfiniteScrollProgress(true);
        }

        // Update flags
        mIsLoading = true;

        // Fetch data from data manager
        mDisposable = mDataManager.getPublicEvents(mPage)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(events -> {
                    // Hide progress wheel
                    if (mPage == 0) {
                        getMvpView().showProgress(false);
                    } else {
                        getMvpView().showInfiniteScrollProgress(false);
                    }

                    // Show message if no results are found
                    if (events.isEmpty() && mPage == 0) {
                        getMvpView().showMessage("Feed not found.");
                        mIsLoading = false;
                        return;
                    }

                    // Clear search results if necessary
                    if (mPage == 0) {
                        getMvpView().clearEvents();
                    }

                    // Update view with new events
                    getMvpView().updateEvents(events);

                    // Update flags
                    mIsLoading = false;
                    mPage++;
                }, error -> {
                    mIsLoading = false;

                    // Log and display error
                    getMvpView().clearEvents();
                    getMvpView().showProgress(false);
                    getMvpView().showMessage(error.getMessage());
                    Timber.e(error, error.getMessage());
                });
    }

    public void refreshEvents() {
        mPage = 0;
        getNextEvents();
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
