package com.danielgauci.gittr.ui.search;

import android.app.SearchManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;

import com.danielgauci.gittr.Gittr;
import com.danielgauci.gittr.R;
import com.danielgauci.gittr.data.model.Event;
import com.danielgauci.gittr.ui.common.EventsAdapter;
import com.danielgauci.gittr.ui.common.InfiniteScrollListener;
import com.danielgauci.gittr.ui.common.SimpleDividerDecoration;
import com.danielgauci.gittr.utils.KeyboardUtils;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;
import timber.log.Timber;

public class SearchActivity extends AppCompatActivity implements SearchMvpView, EventsAdapter.ClickListener {

    @Inject SearchPresenter mPresenter;
    @Inject EventsAdapter mAdapter;

    @BindView(R.id.search_view)
    SearchView mSearchView;
    @BindView(R.id.search_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.search_message)
    AppCompatTextView mMessageTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        // Setup Dagger
        ((Gittr) getApplication()).getAppComponent().inject(this);

        // Bind presenter
        mPresenter.attachView(this);

        setupViews();
        setupSearchBar();
    }

    private void setupViews() {
        // Setup recycler view
        mAdapter.registerEventClickListener(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new SlideInUpAnimator());
        mRecyclerView.addItemDecoration(new SimpleDividerDecoration(this));
        mRecyclerView.addOnScrollListener(new InfiniteScrollListener(layoutManager) {
            @Override
            public void onLoadMore() {
                try {
                    mPresenter.getNextUserEvents();
                } catch (Exception exception) {
                    Timber.e(exception, exception.getMessage());
                }
            }
        });

        // Show hint
        showMessage("Type in a username and press enter to search for a user's feed.");
    }

    private void setupSearchBar() {
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        mSearchView.setIconifiedByDefault(false);
        mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        mSearchView.setQueryHint("Search by username...");
        mSearchView.setImeOptions(mSearchView.getImeOptions() | EditorInfo.IME_ACTION_SEARCH |
                EditorInfo.IME_FLAG_NO_EXTRACT_UI | EditorInfo.IME_FLAG_NO_FULLSCREEN);

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Search on submit, since searching on every text change
                // gets the app blocked from the Github API
                mPresenter.search(query);
                KeyboardUtils.toggleKeyboard(SearchActivity.this);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Clear events once the text field is empty
                if (newText.isEmpty()) {
                    mAdapter.clearEvents();
                    showMessage("Type in a username and press enter to search for a user's feed.");
                }

                return true;
            }
        });
    }


    // Implement MVPView methods

    @Override
    public void showSearchResults(List<Event> searchResults) {
        mAdapter.addEvents(searchResults);
    }

    @Override
    public void clearSearchResults() {
        mAdapter.clearEvents();
    }

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
    public void showProgressWheel(boolean show) {
        mAdapter.showProgressWheel(show);
    }

    @Override
    public void eventSelected(Event event) {
        // TODO: Open detail screen
    }

    @Override
    public void onEventClicked(Event event) {
        mPresenter.showEventDetail(event);
    }
}
