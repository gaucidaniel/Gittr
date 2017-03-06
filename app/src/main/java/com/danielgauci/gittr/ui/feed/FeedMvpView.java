package com.danielgauci.gittr.ui.feed;

import com.danielgauci.gittr.data.model.Event;
import com.danielgauci.gittr.ui.base.MvpView;

import java.util.List;

/**
 * Created by daniel on 2/26/17.
 */

public interface FeedMvpView extends MvpView{

    void showMessage(String message);

    void hideMessage();

    void showProgress(boolean show);

    void showInfiniteScrollProgress(boolean show);

    void clearEvents();

    void updateEvents(List<Event> events);

    void showEventDetail(Event event);
}
