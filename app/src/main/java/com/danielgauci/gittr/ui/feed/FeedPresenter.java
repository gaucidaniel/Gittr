package com.danielgauci.gittr.ui.feed;

import com.danielgauci.gittr.data.DataManager;
import com.danielgauci.gittr.data.model.Event;
import com.danielgauci.gittr.ui.base.BasePresenter;

import org.reactivestreams.Subscription;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by daniel on 2/26/17.
 */

public class FeedPresenter extends BasePresenter<FeedMvpView> {

    private DataManager mDataManager;

    public FeedPresenter() {
        this.mDataManager = new DataManager();
    }

    public void getEvents() {
        checkViewAttached();

        // Hide any messages and show progress wheel
        getMvpView().hideMessage();
        getMvpView().showProgress(true);

        // Subscribe to data changes
        mDataManager.getPublicEvents()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(events -> {
                    // Fetched events
                    getMvpView().showProgress(false);
                    if (events.isEmpty()) {
                        getMvpView().showMessage("No events found");
                    } else {
                        getMvpView().showEvents(events);
                    }
                }, error -> {
                    // Show error to user and log
                    getMvpView().showProgress(false);
                    getMvpView().showMessage(error.getMessage());
                    Timber.e(error, error.getMessage());
                });
    }

    public void onEventSelected(Event event){
        getMvpView().showEventDetail(event);
    }
}
