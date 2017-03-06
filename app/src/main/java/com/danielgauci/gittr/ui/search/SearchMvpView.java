package com.danielgauci.gittr.ui.search;

import com.danielgauci.gittr.data.model.Event;
import com.danielgauci.gittr.ui.base.MvpView;

import java.util.List;

/**
 * Created by daniel on 3/6/17.
 */

public interface SearchMvpView extends MvpView {

    void showSearchResults(List<Event> searchResults);

    void clearSearchResults();

    void showMessage(String message);

    void hideMessage();

    void showProgressWheel(boolean show);

    void eventSelected(Event event);
}
