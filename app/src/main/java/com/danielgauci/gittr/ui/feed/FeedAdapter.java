package com.danielgauci.gittr.ui.feed;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.danielgauci.gittr.R;
import com.danielgauci.gittr.data.model.Event;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import timber.log.Timber;

/**
 * Created by daniel on 2/26/17.
 */

public class FeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Event> mEvents;
    private Context mContext;
    private PrettyTime mPrettyTime;

    public FeedAdapter(Context mContext) {
        this.mContext = mContext;
        this.mEvents = new ArrayList<>();
        this.mPrettyTime = new PrettyTime();
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
            Glide.with(mContext).load(event.getActor().getAvatarUrl()).into(eventViewHolder.profilePictureImageView);

            // Set title
            eventViewHolder.titleTextView.setText(event.getDescription());

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

    public void setEvents(List<Event> mEvents) {
        this.mEvents = mEvents;
        notifyDataSetChanged();
    }

    public class EventViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.event_item_profile_picture) CircleImageView profilePictureImageView;
        @BindView(R.id.event_item_title) AppCompatTextView titleTextView;
        @BindView(R.id.event_item_details) AppCompatTextView detailsTextView;
        @BindView(R.id.event_item_date) AppCompatTextView dateTextView;

        private EventViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
