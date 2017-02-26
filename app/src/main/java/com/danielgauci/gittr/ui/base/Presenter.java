package com.danielgauci.gittr.ui.base;

/**
 * Created by daniel on 2/26/17.
 */

public interface Presenter<V extends MvpView>{

    void attachView(V mvpView);

    void detachView();
}
