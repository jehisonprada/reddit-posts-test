package com.rappi.test.redditpostsviewer.view;

import android.content.Context;

import com.rappi.test.redditpostsviewer.model.entities.Reddit;

/**
 * Created by Jehison on 26/12/2016.
 */
public interface ThumbnailView {

    void notifyInternetConnected();

    void notifyInternetNotConnected();

    void showProgressBar();

    void hideProgressBar();

    Context getActivityContext();

    void setReddit(Reddit reddit);

    void renderRedditPosts();

}
