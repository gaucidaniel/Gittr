package com.danielgauci.gittr.ui.feed;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.danielgauci.gittr.R;
import com.danielgauci.gittr.data.model.Event;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FeedFragment extends Fragment implements FeedMvpView {

    private FeedPresenter mPresenter;

    @BindView(R.id.browse_toolbar) Toolbar mToolbar;
    @BindView(R.id.browse_swipe_refresh_layout) SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.browse_recycler_view) RecyclerView mRecyclerView;
    @BindView(R.id.browse_message) TextViewCompat mMessageTextView;

    public FeedFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create new instance of FeedPresenter
        mPresenter = new FeedPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_feed, container, false);
        ButterKnife.bind(rootView);

        // Attach fragment to presenter
        mPresenter.attachView(this);

        // Setup views and get events
        setupViews();
        mPresenter.getEvents();

        return rootView;
    }

    private void setupViews(){
        // Setup recycler view
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    // Implement MVPView methods
    @Override
    public void showMessage(String message) {

    }

    @Override
    public void hideMessage() {

    }

    @Override
    public void showProgress(boolean show) {

    }

    @Override
    public void showEvents(List<Event> events) {

    }
}
