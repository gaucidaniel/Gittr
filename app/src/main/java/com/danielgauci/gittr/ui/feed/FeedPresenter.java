package com.danielgauci.gittr.ui.feed;

import com.danielgauci.gittr.data.DataManager;
import com.danielgauci.gittr.data.model.Event;
import com.danielgauci.gittr.ui.base.BasePresenter;

import org.reactivestreams.Subscription;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by daniel on 2/26/17.
 */

public class FeedPresenter extends BasePresenter<FeedMvpView> {

    private DataManager mDataManager;
    private int eventsPage = 0;
    private boolean isLoading = false;

    @Inject
    public FeedPresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

    public void getNextEvents() {
        checkViewAttached();

        // Stop if already loading
        if (isLoading) {
            return;
        }

        // Hide any messages and show progress wheel
        getMvpView().hideMessage();

        if (eventsPage == 0) {
            getMvpView().showProgress(true);
        } else {
            getMvpView().showInfiniteScrollProgress(true);
        }

        // Update flags
        isLoading = true;

        // Fetch data from data manager
        mDataManager.getPublicEvents(eventsPage)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(events -> {
                    // Hide progress wheel
                    if (eventsPage == 0) {
                        getMvpView().showProgress(false);
                    } else {
                        getMvpView().showInfiniteScrollProgress(false);
                    }

                    // Show message if no results are found
                    if (events.isEmpty() && eventsPage == 0) {
                        getMvpView().showMessage("Feed not found.");
                        isLoading = false;
                        return;
                    }

                    // Clear search results if necessary
                    if (eventsPage == 0) {
                        getMvpView().clearEvents();
                    }

                    // Update view with new events
                    getMvpView().updateEvents(events);

                    // Update flags
                    isLoading = false;
                    eventsPage++;
                }, error -> {
                    isLoading = false;

                    // Log and display error
                    getMvpView().showProgress(false);
                    getMvpView().showMessage(error.getMessage());
                    Timber.e(error, error.getMessage());
                });
    }

    public void refreshEvents() {
        eventsPage = 0;
        getNextEvents();
    }


    public void onEventSelected(Event event) {
        getMvpView().showEventDetail(event);
    }
}
