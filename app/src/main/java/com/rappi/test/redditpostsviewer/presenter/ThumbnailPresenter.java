package com.rappi.test.redditpostsviewer.presenter;

import com.rappi.test.redditpostsviewer.model.entities.Reddit;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Jehison on 26/12/2016.
 */
public interface ThumbnailPresenter {

    void manageInternetConnection(boolean internetConnected);

    void readJSONInformationFromReddit();

    void onReadingOnlineSuccess(Reddit reddit, Response response);

    void onReadingOnlineFailure(RetrofitError error);

}
