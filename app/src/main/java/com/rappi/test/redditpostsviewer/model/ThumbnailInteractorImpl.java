package com.rappi.test.redditpostsviewer.model;

import android.content.Context;

import com.rappi.test.redditpostsviewer.model.adapter.RestAdapterHelper;
import com.rappi.test.redditpostsviewer.model.entities.Reddit;
import com.rappi.test.redditpostsviewer.presenter.ThumbnailPresenter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Jehison on 26/12/2016.
 */
public class ThumbnailInteractorImpl implements ThumbnailInteractor {

    ThumbnailPresenter thumbnailPresenter;

    public ThumbnailInteractorImpl(ThumbnailPresenter thumbnailPresenter){
        this.thumbnailPresenter = thumbnailPresenter;
    }

    @Override
    public void getJSONFromReddit() {
        RestAdapterHelper.API_URL="https://www.reddit.com";
        RestAdapterHelper.getApiClientMethods().getRedditPosts(new Callback<Reddit>() {

            @Override
            public void success(Reddit reddit, Response response) {
                thumbnailPresenter.onReadingOnlineSuccess(reddit, response);
            }

            @Override
            public void failure(RetrofitError error) {
                thumbnailPresenter.onReadingOnlineFailure(error);
            }
        });
    }

    @Override
    public Reddit getJSONFromCacheFile(Context activityContext, String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fis = activityContext.openFileInput(fileName);
        ObjectInputStream is = new ObjectInputStream(fis);
        Reddit reddit = (Reddit) is.readObject();
        is.close();
        fis.close();
        return reddit;
    }

    @Override
    public void saveCacheFile(Context activityContext, String fileName, Reddit reddit) throws IOException {
        FileOutputStream fos = activityContext.openFileOutput(fileName, Context.MODE_PRIVATE);
        ObjectOutputStream os = new ObjectOutputStream(fos);
        os.writeObject(reddit);
        os.close();
        fos.close();
    }
}
