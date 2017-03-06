package com.danielgauci.gittr.ui.search;

import android.content.Context;

import com.danielgauci.gittr.data.DataManager;
import com.danielgauci.gittr.data.model.Event;
import com.danielgauci.gittr.ui.base.BasePresenter;
import com.danielgauci.gittr.utils.NetworkUtils;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by daniel on 3/6/17.
 */

public class SearchPresenter extends BasePresenter<SearchMvpView> {

    private Context mContext;
    private DataManager mDataManager;
    private String mQuery;
    private int mPage;
    private boolean mIsLoading;

    @Inject
    public SearchPresenter(DataManager mDataManager, Context context) {
        this.mContext = context;
        this.mDataManager = mDataManager;
        this.mPage = 0;
        this.mIsLoading = false;
        this.mQuery = null;
    }

    public void search(String username){
        checkViewAttached();

        // Check network
        if (!NetworkUtils.isInternetAvailable(mContext)){
            getMvpView().showProgressWheel(false);
            getMvpView().clearSearchResults();
            getMvpView().showMessage("No internet connection.");
            return;
        }

        // Reset current state
        mPage = 0;
        mQuery = username;
        getMvpView().clearSearchResults();

        getNextUserEvents();
    }

    public void getNextUserEvents(){
        if (mQuery == null){
            Timber.e("Search query must be initialized by calling search(String username)" +
                    "before requesting the next user events.");
            return;
        }

        if (!mIsLoading) {
            checkViewAttached();
            mIsLoading = true;

            // Show progress wheel
            getMvpView().showProgressWheel(true);
            getMvpView().hideMessage();

            // Fetch data from data manager
            mDataManager.getUserEvents(mQuery, mPage)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(events -> {
                        // Hide progress wheel
                        getMvpView().showProgressWheel(false);

                        // Show message if no results are found
                        if (events.isEmpty() && mPage == 0) {
                            getMvpView().showMessage("No feed for " + mQuery + " was found.");
                            mIsLoading = false;
                            return;
                        }

                        // Clear search results if necessary
                        if (mPage == 0){
                            getMvpView().clearSearchResults();
                        }

                        // Update view with new events
                        getMvpView().showSearchResults(events);

                        // Update flags
                        mIsLoading = false;
                        mPage++;
                    }, error -> {
                        mIsLoading = false;

                        // Log and display error
                        getMvpView().showProgressWheel(false);
                        getMvpView().showMessage(error.getMessage());
                        Timber.e(error, error.getMessage());
                    });
        }
    }

    public void showEventDetail(Event event){
        getMvpView().eventSelected(event);
    }
}
