package com.rappi.test.redditpostsviewer.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.rappi.test.redditpostsviewer.R;
import com.rappi.test.redditpostsviewer.model.entities.Reddit;
import com.rappi.test.redditpostsviewer.presenter.ThumbnailPresenter;
import com.rappi.test.redditpostsviewer.presenter.ThumbnailPresenterImpl;
import com.rappi.test.redditpostsviewer.util.DimensionsUtil;
import com.rappi.test.redditpostsviewer.util.NetworkUtil;

public class ThumbnailActivity extends Activity implements ThumbnailView {

    private ThumbnailPresenter thumbnailPresenter;

    private ProgressBar progressBar;

    private RecyclerView recyclerView;

    private Reddit reddit = new Reddit();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thumbnail);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        thumbnailPresenter = new ThumbnailPresenterImpl(this);
        thumbnailPresenter.manageInternetConnection(NetworkUtil.isInternetConnected(this));
        thumbnailPresenter.readJSONInformationFromReddit();
    }

    @Override
    public void notifyInternetConnected() {
        Toast internetToast = Toast.makeText(this, "Internet is on. Loading online...", Toast.LENGTH_LONG);
        internetToast.show();
    }

    @Override
    public void notifyInternetNotConnected() {
        Toast internetToast = Toast.makeText(this, "Internet is off. Loading from cache...", Toast.LENGTH_LONG);
        internetToast.show();
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public Context getActivityContext(){
        return this;
    }

    @Override
    public void setReddit(Reddit reddit) {
        this.reddit = reddit;
    }

    @Override
    public void renderRedditPosts() {
        RecyclerView.LayoutManager layoutManager;
        if (DimensionsUtil.isTablet(this)) {
            layoutManager = new GridLayoutManager(this, 3);
        } else {
            layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        }
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(reddit.getData().getChildren(), this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}
