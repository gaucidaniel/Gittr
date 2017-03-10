package com.danielgauci.gittr.ui.feeddetail;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.danielgauci.gittr.Gittr;
import com.danielgauci.gittr.R;
import com.danielgauci.gittr.data.model.Event;
import com.pnikosis.materialishprogress.ProgressWheel;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import br.tiagohm.markdownview.MarkdownView;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class FeedDetailActivity extends AppCompatActivity implements FeedDetailMvpView {

    public final static String EXTRA_EVENT = "extraEvent";

    @BindView(R.id.feed_detail_toolbar) Toolbar mToolbar;
    @BindView(R.id.event_item_title) TextView mEventDescriptionTextView;
    @BindView(R.id.event_item_details) TextView mEventMessageTextView;
    @BindView(R.id.event_item_profile_picture) CircleImageView mEventProfilePictureView;
    @BindView(R.id.event_item_date) TextView mEventDateTextView;
    @BindView(R.id.feed_detail_repo_title) TextView mRepoTitle;
    @BindView(R.id.feed_detail_repo_description) TextView mRepoDescription;
    @BindView(R.id.feed_detail_repo_language) TextView mRepoLanguage;
    @BindView(R.id.feed_detail_repo_forks) TextView mRepoForks;
    @BindView(R.id.feed_detail_repo_stars) TextView mRepoStars;
    @BindView(R.id.feed_detail_repo_watches) TextView mRepoWatches;
    @BindView(R.id.feed_detail_repo_readme) MarkdownView mReadmeMarkdownView;
    @BindView(R.id.feed_detail_repo_message) TextView mRepoMessage;
    @BindView(R.id.feed_detail_repo_details) LinearLayout mRepoDetails;
    @BindView(R.id.feed_detail_repo_progress_wheel) ProgressWheel mRepoProgressWheel;

    @Inject
    FeedDetailPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_detail);
        ButterKnife.bind(this);

        // Setup dagger
        ((Gittr) getApplication()).getAppComponent().inject(this);

        // Setup presenter
        Event event = getIntent().getParcelableExtra(EXTRA_EVENT);
        mPresenter.attachView(this);
        mPresenter.setEvent(event);

        // Setup back button
        mToolbar.setNavigationOnClickListener((View v) -> {
            finish();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null){
            mPresenter.detachView();
        }

        super.onDestroy();
    }

    // Implement MVP View

    @Override
    public void setEventDescription(SpannableStringBuilder description) {
        mEventDescriptionTextView.setText(description);
    }

    @Override
    public void setEventMessage(String message) {
        if (TextUtils.isEmpty(message)) {
            mEventMessageTextView.setVisibility(View.GONE);
        } else {
            mEventMessageTextView.setVisibility(View.VISIBLE);
            mEventMessageTextView.setText(message);
        }
    }

    @Override
    public void setEventDate(String dateString) {
        mEventDateTextView.setText(dateString);
    }

    @Override
    public void setEventProfilePicture(String profilePictureUrl) {
        // Set profile picture
        Picasso.with(this)
                .load(profilePictureUrl)
                .placeholder(R.color.greyLight)
                .into(mEventProfilePictureView);
    }

    @Override
    public void setRepoTitle(String title) {
        mRepoTitle.setText(title);
    }

    @Override
    public void setRepoDescription(String description) {
        mRepoDescription.setText(description);
    }

    @Override
    public void setRepoLanguage(String language) {
        // Highlight language to primary color
        SpannableStringBuilder languageBuilder = new SpannableStringBuilder();
        languageBuilder.append("Written using ");

        int start = languageBuilder.length();
        languageBuilder.append(language);

        languageBuilder.setSpan(new ForegroundColorSpan(
                        ContextCompat.getColor(this, R.color.colorPrimary)),
                        start,
                        languageBuilder.length(),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Update text view
        mRepoLanguage.setText(languageBuilder);
    }

    @Override
    public void setRepoStarCount(int starCount) {
        mRepoStars.setText(String.valueOf(starCount));
    }

    @Override
    public void setRepoForkCount(int forkCount) {
        mRepoForks.setText(String.valueOf(forkCount));
    }

    @Override
    public void setWatchCount(int watchCount) {
        mRepoWatches.setText(String.valueOf(watchCount));
    }

    @Override
    public void setRepoReadme(String readme) {
        mReadmeMarkdownView.loadMarkdown(readme, "file:///android_asset/css/markdown.css");
        mReadmeMarkdownView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showRepoDetails(boolean show) {
        mRepoDetails.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showProgress(boolean show) {
        mRepoProgressWheel.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showMessage(String message) {
        mRepoMessage.setVisibility(View.VISIBLE);
        mRepoMessage.setText(message);
    }

    @Override
    public void hideMessage() {
        mRepoMessage.setVisibility(View.GONE);
    }
}
