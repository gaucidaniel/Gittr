package com.danielgauci.gittr.ui.feed;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.danielgauci.gittr.R;
import com.danielgauci.gittr.data.model.Filter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Created by daniel on 3/7/17.
 */

public class FiltersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private FilterListener mListener;
    private List<Filter> mFilters;

    @Inject
    public FiltersAdapter(Context mContext) {
        this.mContext = mContext;
        this.mFilters = Filter.getAllFilters();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FilterViewHolder(LayoutInflater.from(mContext).inflate(R.layout.view_filter, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FilterViewHolder){
            Filter filter = mFilters.get(position);

            // Set filter name
            ((FilterViewHolder) holder).nameTextView.setText(filter.getName() + " Events");

            // Set filter opacity
            ((FilterViewHolder) holder).itemView.setAlpha(filter.isEnabled() ? .5f : 1f);
        }
    }

    @Override
    public int getItemCount() {
        return mFilters.size();
    }

    public void registerFilterListner(FilterListener filterListener){
        mListener = filterListener;
    }

    public class FilterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.filter_item_name) TextView nameTextView;

        public FilterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListener != null) {
                Filter selectedFilter = mFilters.get(getAdapterPosition());
                if (selectedFilter.toggle()) {
                    mListener.onFilterAdded(selectedFilter);
                    itemView.setAlpha(.5f);
                } else {
                    mListener.onFilterRemoved(selectedFilter);
                    itemView.setAlpha(1f);
                }
            } else {
                Timber.e("Filter listener must be register to receive filter events.");
            }
        }
    }

    public interface FilterListener{

        void onFilterAdded(Filter filter);

        void onFilterRemoved(Filter filter);
    }
}
