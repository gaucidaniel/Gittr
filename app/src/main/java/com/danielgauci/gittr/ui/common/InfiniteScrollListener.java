package com.danielgauci.gittr.ui.common;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by daniel on 3/5/17.
 */

public class InfiniteScrollListener extends RecyclerView.OnScrollListener {

    private LinearLayoutManager mLayoutManager;

    public InfiniteScrollListener(LinearLayoutManager layoutManager) {
        this.mLayoutManager = layoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        int visibleCount = mLayoutManager.getChildCount();
        int totalCount = mLayoutManager.getItemCount();
        int firstVisiblePosition = mLayoutManager.findFirstVisibleItemPosition();

        if ((visibleCount + firstVisiblePosition >= totalCount) && firstVisiblePosition >= 0 && totalCount >= 30){
            onLoadMore();
        }
    }

    public void onLoadMore(){

    }
}
