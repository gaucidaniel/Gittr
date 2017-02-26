package com.danielgauci.gittr.ui.base;

/**
 * Created by daniel on 2/26/17.
 */

public class BasePresenter<T extends MvpView> implements Presenter<T> {

    private T mMvpView;

    @Override
    public void attachView(T mvpView) {
        mMvpView = mvpView;
    }

    @Override
    public void detachView() {
        mMvpView = null;
    }

    public boolean isViewAttached(){
        return mMvpView != null;
    }

    public T getMvpView(){
        return mMvpView;
    }

    public void checkViewAttached(){
        if (!isViewAttached()){
            throw new RuntimeException("Please attach a view before using the presenter.");
        }
    }
}
