package com.danielgauci.gittr.ui.feed;

import com.danielgauci.gittr.data.DataManager;
import com.danielgauci.gittr.data.model.Event;
import com.danielgauci.gittr.ui.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by daniel on 2/26/17.
 */

public class FeedPresenter extends BasePresenter<FeedMvpView> {

    private DataManager mDataManager;
    private int mPage = 0;
    private boolean mIsLoading = false;

    @Inject
    public FeedPresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

    public void getNextEvents() {
        checkViewAttached();

        // Stop if already loading
        if (mIsLoading) {
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
        mDataManager.getPublicEvents(mPage)
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
                    getMvpView().showProgress(false);
                    getMvpView().showMessage(error.getMessage());
                    Timber.e(error, error.getMessage());
                });
    }

    public void refreshEvents() {
        mPage = 0;
        getNextEvents();
    }


    public void onEventSelected(Event event) {
        getMvpView().showEventDetail(event);
    }
}
