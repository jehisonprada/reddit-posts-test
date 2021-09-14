package com.rappi.test.redditpostsviewer.presenter;

import android.util.Log;

import com.rappi.test.redditpostsviewer.model.ThumbnailInteractor;
import com.rappi.test.redditpostsviewer.model.ThumbnailInteractorImpl;
import com.rappi.test.redditpostsviewer.model.entities.Reddit;
import com.rappi.test.redditpostsviewer.view.ThumbnailView;

import java.io.IOException;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Jehison on 26/12/2016.
 */
public class ThumbnailPresenterImpl implements ThumbnailPresenter {

    private ThumbnailView thumbnailView;
    private ThumbnailInteractor thumbnailInteractor;

    private static final String FILE_NAME = "redditCache.json";

    private boolean internetConnected;

    public ThumbnailPresenterImpl(ThumbnailView thumbnailView){
        this.thumbnailView = thumbnailView;
        thumbnailInteractor = new ThumbnailInteractorImpl(this);
    }

    @Override
    public void manageInternetConnection(boolean internetConnected) {
        if(internetConnected){
            thumbnailView.notifyInternetConnected();
            this.internetConnected = true;
        } else {
            thumbnailView.notifyInternetNotConnected();
            this.internetConnected = false;
        }
    }

    @Override
    public void readJSONInformationFromReddit() {
        if(internetConnected){
            readJSONInformationOnline();
        } else{
            readJSONInformationFromCache();
        }
    }

    private void readJSONInformationOnline(){
        thumbnailView.showProgressBar();
        thumbnailInteractor.getJSONFromReddit();
    }

    @Override
    public void onReadingOnlineSuccess(Reddit reddit, Response response) {
        //Saving info on a file for cache
        try {
            thumbnailInteractor.saveCacheFile(thumbnailView.getActivityContext(), FILE_NAME, reddit);
        } catch (IOException e) {
            e.printStackTrace();
        }
        thumbnailView.setReddit(reddit);
        thumbnailView.hideProgressBar();
        thumbnailView.renderRedditPosts();
    }

    @Override
    public void onReadingOnlineFailure(RetrofitError error) {
        thumbnailView.hideProgressBar();
    }

    private void readJSONInformationFromCache(){
        thumbnailView.showProgressBar();
        try {
            Reddit reddit = thumbnailInteractor.getJSONFromCacheFile(thumbnailView.getActivityContext(), FILE_NAME);
            thumbnailView.setReddit(reddit);
            thumbnailView.hideProgressBar();
            thumbnailView.renderRedditPosts();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
