package com.rappi.test.redditpostsviewer.model;

import android.content.Context;

import com.rappi.test.redditpostsviewer.model.entities.Reddit;

import java.io.IOException;

/**
 * Created by Jehison on 26/12/2016.
 */
public interface ThumbnailInteractor {

    void getJSONFromReddit();

    Reddit getJSONFromCacheFile(Context activityContext, String fileName) throws IOException, ClassNotFoundException;

    void saveCacheFile(Context activityContext, String fileName, Reddit reddit) throws IOException;

}
