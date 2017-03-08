package com.danielgauci.gittr.ui.common;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.danielgauci.gittr.R;
import com.danielgauci.gittr.data.model.Event;
import com.jakewharton.rxbinding.view.RxView;
import com.squareup.picasso.Picasso;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import timber.log.Timber;

/**
 * Created by daniel on 2/26/17.
 */

public class EventsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int TYPE_EVENT = 10;
    private final int TYPE_PROGRESS = 20;

    private EventsListener mEventsListener;
    private List<Event> mAllEvents;
    private List<Event> mFilteredEvents;
    private List<String> mEnabledFilters;
    private Context mContext;
    private PrettyTime mPrettyTime;
    private boolean mProgressWheelVisible;

    @Inject
    public EventsAdapter(Context mContext, PrettyTime prettyTime) {
        this.mContext = mContext;
        this.mAllEvents = new ArrayList<>();
        this.mFilteredEvents = new ArrayList<>();
        this.mEnabledFilters = new ArrayList<>();
        this.mPrettyTime = prettyTime;
        this.mProgressWheelVisible = false;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            default:
            case TYPE_EVENT:
                return new EventViewHolder(LayoutInflater.from(mContext).inflate(R.layout.view_event, parent, false));

            case TYPE_PROGRESS:
                return new ProgressViewHolder(LayoutInflater.from(mContext).inflate(R.layout.view_progress, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // Set event data in view
        if (holder instanceof EventViewHolder){
            Event event = mFilteredEvents.get(position);
            EventViewHolder eventViewHolder = (EventViewHolder) holder;

            // Set profile picture
            Picasso.with(mContext)
                    .load(event.getActor().getAvatarUrl())
                    .placeholder(R.color.greyLight)
                    .into(eventViewHolder.profilePictureImageView);

            // Set title
            eventViewHolder.titleTextView.setText(event.getDescriptionSpannable(mContext));

            // Set details
            if (event.getMessage().isEmpty()){
                eventViewHolder.detailsTextView.setVisibility(View.GONE);
            } else  {
                eventViewHolder.detailsTextView.setVisibility(View.VISIBLE);
                eventViewHolder.detailsTextView.setText(event.getMessage());
            }

            // Set date
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");
                Date createdAt = dateFormat.parse(event.getCreatedAt());
                String dateString = mPrettyTime.format(createdAt);
                eventViewHolder.dateTextView.setText(dateString);
            } catch (ParseException e){
                Timber.e("Failed to parse event time", e);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mProgressWheelVisible ? mFilteredEvents.size() + 1 : mFilteredEvents.size();
    }


    @Override
    public int getItemViewType(int position) {
        if (mProgressWheelVisible && position >= mFilteredEvents.size()){
            return TYPE_PROGRESS;
        } else  {
            return TYPE_EVENT;
        }
    }

    public void addEvents(List<Event> newEvents) {
        // Hide progress wheel when new events are added
        if (mProgressWheelVisible){
            showProgressWheel(false);
        }

        // Update all events
        mAllEvents.addAll(newEvents);

        // Add new filtered events to the currently show ones
        int oldSize = mFilteredEvents.size();
        mFilteredEvents.addAll(filterEvents(newEvents, mEnabledFilters));
        notifyItemRangeInserted(oldSize, mFilteredEvents.size());

        checkIfFilteredEventsEmpty();
    }

    public void clearEvents(){
        // Clear all events from adapter
        this.mAllEvents.clear();
        this.mFilteredEvents.clear();
        notifyDataSetChanged();
    }

    public void addFilter(String filter){
        mEnabledFilters.add(filter);
        mFilteredEvents = filterEvents(mAllEvents, mEnabledFilters);
        notifyDataSetChanged();
    }

    public void removeFilter(String filter){
        mEnabledFilters.remove(filter);
        mFilteredEvents = filterEvents(mAllEvents, mEnabledFilters);
        notifyDataSetChanged();
    }

    public List<Event> filterEvents(List<Event> allEvents, List<String> enabledFilters){
        List<Event> filteredEvents = new ArrayList<>();
        for (Event event : allEvents) {
            if (!enabledFilters.contains(event.getType())){
                filteredEvents.add(event);
            }
        }

        return filteredEvents;
    }

    public void showProgressWheel(boolean show){
        mProgressWheelVisible = show;
        notifyDataSetChanged();
    }

    public void registerEventClickListener(EventsListener mEventsListener) {
        // Register click listener for events
        this.mEventsListener = mEventsListener;
    }

    private void checkIfFilteredEventsEmpty(){
        if (mFilteredEvents.isEmpty() && !mAllEvents.isEmpty()){
            mEventsListener.onFilterResultsEmpty();
        }
    }

    public class EventViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.event_item_profile_picture) CircleImageView profilePictureImageView;
        @BindView(R.id.event_item_title) AppCompatTextView titleTextView;
        @BindView(R.id.event_item_details) AppCompatTextView detailsTextView;
        @BindView(R.id.event_item_date) AppCompatTextView dateTextView;

        private EventViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            // Subscribe to click events
            RxView.clicks(itemView).subscribe((view) -> mEventsListener.onEventClicked(mAllEvents.get(getAdapterPosition())));
        }
    }

    public class ProgressViewHolder extends RecyclerView.ViewHolder{

        private ProgressViewHolder(View itemView) {
            super(itemView);
        }
    }

    public interface EventsListener {

        void onFilterResultsEmpty();

        void onEventClicked(Event event);
    }
}
