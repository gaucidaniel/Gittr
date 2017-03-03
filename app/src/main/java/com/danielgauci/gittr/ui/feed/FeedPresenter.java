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
        if (isLoading){
            return;
        }

        // Hide any messages and show progress wheel
        getMvpView().hideMessage();
        getMvpView().showProgress(true);

        // Update flags
        isLoading = true;

        // Subscribe to data change
        mDataManager.getPublicEvents(eventsPage)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(events -> {
                    // Update flags
                    isLoading = false;

                    // Fetched events
                    getMvpView().showProgress(false);
                    if (events.isEmpty()) {
                        getMvpView().showMessage("No events found");
                    } else {
                        getMvpView().updateEvents(events, eventsPage == 0);
                    }

                    // Increment page
                    eventsPage++;
                }, error -> {
                    // Show error to user and log
                    getMvpView().showProgress(false);
                    getMvpView().showMessage(error.getMessage());
                    Timber.e(error, error.getMessage());
                });
    }

    public void refreshEvents(){
        eventsPage = 0;
        getNextEvents();
    }


    public void onEventSelected(Event event){
        getMvpView().showEventDetail(event);
    }
}
