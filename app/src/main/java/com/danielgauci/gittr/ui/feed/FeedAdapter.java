package com.danielgauci.gittr.ui.feed;

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

public class FeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ClickListener mClickListener;
    private List<Event> mEvents;
    private Context mContext;
    private PrettyTime mPrettyTime;

    @Inject
    public FeedAdapter(Context mContext, PrettyTime prettyTime) {
        this.mContext = mContext;
        this.mEvents = new ArrayList<>();
        this.mPrettyTime = prettyTime;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new EventViewHolder(LayoutInflater.from(mContext).inflate(R.layout.view_event, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // Set event data in view
        Event event = mEvents.get(position);
        if (holder instanceof EventViewHolder){
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
        return mEvents.size();
    }

    public void addEvents(List<Event> newEvents) {
        int oldSize = mEvents.size();
        mEvents.addAll(newEvents);
        notifyItemRangeInserted(oldSize, mEvents.size());
    }

    public void setEvents(List<Event> events) {
        boolean eventsVisible = this.mEvents.size() != 0;
        this.mEvents = events;
        if (eventsVisible){
            notifyItemRangeChanged(0, mEvents.size());
        } else {
            notifyItemRangeInserted(0, mEvents.size());
        }
    }

    public void setmClickListener(ClickListener mClickListener) {
        this.mClickListener = mClickListener;
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
            RxView.clicks(itemView).subscribe((view) -> mClickListener.onEventClicked(mEvents.get(getAdapterPosition())));
        }
    }

    public interface ClickListener{
        void onEventClicked(Event event);
    }
}
