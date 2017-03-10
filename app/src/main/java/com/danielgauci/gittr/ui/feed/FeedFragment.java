package com.danielgauci.gittr.ui.feed;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.danielgauci.gittr.Gittr;
import com.danielgauci.gittr.R;
import com.danielgauci.gittr.data.model.Event;
import com.danielgauci.gittr.data.model.Filter;
import com.danielgauci.gittr.ui.common.EventsAdapter;
import com.danielgauci.gittr.ui.common.InfiniteScrollListener;
import com.danielgauci.gittr.ui.common.SimpleDividerDecoration;
import com.danielgauci.gittr.ui.feeddetail.FeedDetailActivity;
import com.danielgauci.gittr.ui.search.SearchActivity;
import com.jakewharton.rxbinding.support.v4.widget.RxSwipeRefreshLayout;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class FeedFragment extends Fragment implements FeedMvpView, EventsAdapter.EventsListener, FiltersAdapter.FilterListener {

    @Inject FeedPresenter mPresenter;
    @Inject EventsAdapter mEventsAdapter;
    @Inject FiltersAdapter mFiltersAdapter;

    @BindView(R.id.browse_drawer) DrawerLayout mDrawerLayout;
    @BindView(R.id.browse_toolbar) Toolbar mToolbar;
    @BindView(R.id.browse_swipe_refresh_layout) SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.browse_recycler_view) RecyclerView mRecyclerView;
    @BindView(R.id.browse_filters_recycler_view) RecyclerView mFiltersRecyclerView;
    @BindView(R.id.browse_message) AppCompatTextView mMessageTextView;

    public FeedFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Setup dagger
        ((Gittr) getActivity().getApplication()).getAppComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_feed, container, false);
        ButterKnife.bind(this, rootView);
        setHasOptionsMenu(true);

        // Attach fragment to presenter
        mPresenter.attachView(this);

        // Setup views and get events
        setupViews();
        mPresenter.getNextEvents();

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.feed_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.feed_menu_search:
                View searchIconView = mToolbar.findViewById(R.id.feed_menu_search);
                Bundle options = ActivityOptionsCompat
                        .makeSceneTransitionAnimation(getActivity(), searchIconView, "transition_search_back")
                        .toBundle();

                startActivity(new Intent(getActivity(), SearchActivity.class), options);
                break;

            case R.id.feed_menu_filter:
                mDrawerLayout.openDrawer(Gravity.END);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupViews() {
        // Setup toolbar
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);

        // Setup swipe refresh layout
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark);
        RxSwipeRefreshLayout.refreshes(mSwipeRefreshLayout)
                .subscribe((view) -> mPresenter.refreshEvents());

        // Setup events recycler view
        mEventsAdapter.registerEventClickListener(this);

        LinearLayoutManager eventsLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(eventsLayoutManager);
        mRecyclerView.setAdapter(mEventsAdapter);
        mRecyclerView.setItemAnimator(new SlideInUpAnimator());
        mRecyclerView.addItemDecoration(new SimpleDividerDecoration(getActivity()));
        mRecyclerView.addOnScrollListener(new InfiniteScrollListener(eventsLayoutManager) {
            @Override
            public void onLoadMore() {
                mPresenter.getNextEvents();
            }
        });

        // Setup filters recycler view
        mFiltersAdapter.registerFilterListner(this);
        mFiltersRecyclerView.setAdapter(mFiltersAdapter);
        mFiltersRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    // Implement MVPView Interface

    @Override
    public void showMessage(String message) {
        mMessageTextView.setVisibility(View.VISIBLE);
        mMessageTextView.setText(message);
    }

    @Override
    public void hideMessage() {
        mMessageTextView.setVisibility(View.GONE);
    }

    @Override
    public void showProgress(boolean show) {
        mSwipeRefreshLayout.setRefreshing(show);
    }

    @Override
    public void showInfiniteScrollProgress(boolean show) {
        mEventsAdapter.showProgressWheel(show);
    }

    @Override
    public void updateEvents(List<Event> events) {
        mEventsAdapter.addEvents(events);
    }

    @Override
    public void clearEvents() {
        mEventsAdapter.clearEvents();
    }

    @Override
    public void showEventDetail(Event event) {
        Intent intent = new Intent(getActivity(), FeedDetailActivity.class);
        intent.putExtra(FeedDetailActivity.EXTRA_EVENT, event);
        startActivity(intent);

    }

    // Implement Adapter Click listener

    @Override
    public void onFilterResultsEmpty() {
        showMessage("No events found. Adjust the filters to show more events.");
    }

    @Override
    public void onEventClicked(Event event) {
        mPresenter.onEventSelected(event);
    }

    // Implement Filter listener

    @Override
    public void onFilterAdded(Filter filter) {
        mEventsAdapter.addFilter(filter.getKey());
    }

    @Override
    public void onFilterRemoved(Filter filter) {
        mEventsAdapter.removeFilter(filter.getKey());
    }
}
