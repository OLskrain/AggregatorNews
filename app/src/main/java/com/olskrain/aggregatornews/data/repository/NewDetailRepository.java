package com.olskrain.aggregatornews.data.repository;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.olskrain.aggregatornews.Common.NetworkStatus;
import com.olskrain.aggregatornews.data.api.HTTPDataHandler;

/**
 * Created by Andrey Ievlev on 11,Май,2019
 */

public class NewDetailRepository implements INewsDetailRepository{

    public interface IResponseServerCallback {
        void onMessageStatus(String message);
        void onWebPage(String webPage);
    }

    private static final String NO_CONNECTION = "Нет подключения";
    private IResponseServerCallback callback;
    private String urlNew;

    public NewDetailRepository(String urlNew) {
        this.urlNew = urlNew;
    }

    public void registerCallBack(IResponseServerCallback callback) {
        this.callback = callback;
    }

    @Override
    public void getWebPage() {
        if (NetworkStatus.isOnline()){
            getData(urlNew);
        } else {
            callback.onMessageStatus(NO_CONNECTION);
        }
    }


    public void getData(String urlNew) {
        @SuppressLint("StaticFieldLeak") AsyncTask<String, String, String> loadRSSAsync = new AsyncTask<String, String, String>() {

            @Override
            protected String doInBackground(String... requestParameters) {
                HTTPDataHandler httpDataHandler = new HTTPDataHandler();
                return httpDataHandler.getHTTPData(requestParameters[0]);
            }

            @Override
            protected void onPostExecute(String webPage) {
                callback.onWebPage(webPage);
            }
        };
        loadRSSAsync.execute(urlNew);
    }
}
