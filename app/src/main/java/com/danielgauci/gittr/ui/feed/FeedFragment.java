package com.danielgauci.gittr.ui.feed;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.danielgauci.gittr.Gittr;
import com.danielgauci.gittr.R;
import com.danielgauci.gittr.data.model.Event;
import com.jakewharton.rxbinding.support.v4.widget.RxSwipeRefreshLayout;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.plugins.RxAndroidPlugins;

public class FeedFragment extends Fragment implements FeedMvpView, FeedAdapter.ClickListener {

    @Inject FeedPresenter mPresenter;
    private FeedAdapter mAdapter;

    @BindView(R.id.browse_toolbar) Toolbar mToolbar;
    @BindView(R.id.browse_swipe_refresh_layout) SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.browse_recycler_view) RecyclerView mRecyclerView;
    @BindView(R.id.browse_message) AppCompatTextView mMessageTextView;

    public FeedFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Setup dagger
        ((Gittr)getActivity().getApplication()).getAppComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_feed, container, false);
        ButterKnife.bind(this, rootView);

        // Attach fragment to presenter
        mPresenter.attachView(this);

        // Setup views and get events
        setupViews();
        mPresenter.getEvents();

        return rootView;
    }

    private void setupViews(){
        // Setup swipe refresh layout
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.greyLight);
        RxSwipeRefreshLayout.refreshes(mSwipeRefreshLayout)
                .subscribe((view) -> mPresenter.getEvents());

        // Setup recycler view
        mAdapter = new FeedAdapter(getActivity());
        mAdapter.setmClickListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), RecyclerView.VERTICAL));
    }

    @Override
    public void showMessage(String message) {
        mMessageTextView.setVisibility(View.GONE);
        mMessageTextView.setText(message);
    }

    // Implement MVPView Interface

    @Override
    public void hideMessage() {
        mMessageTextView.setVisibility(View.GONE);
    }

    @Override
    public void showProgress(boolean show) {
        mSwipeRefreshLayout.setRefreshing(show);
    }

    @Override
    public void showEvents(List<Event> events) {
        mAdapter.setEvents(events);
    }

    @Override
    public void showEventDetail(Event event) {
        // TODO: Open detail activity
    }

    // Implement Adapter Click listener

    @Override
    public void onEventClicked(Event event) {
        mPresenter.onEventSelected(event);
    }
}
