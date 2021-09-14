package com.rappi.test.redditpostsviewer.model.api_interfaces;

import com.rappi.test.redditpostsviewer.model.entities.Reddit;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Jehison on 26/04/2016.
 */
public interface ApiClientMethods {

    @GET("/reddits.json")
    void getRedditPosts(Callback<Reddit> cb);

}
