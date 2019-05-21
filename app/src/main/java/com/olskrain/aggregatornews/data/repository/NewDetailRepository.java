package com.olskrain.aggregatornews.data.repository;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.olskrain.aggregatornews.Common.NetworkStatus;
import com.olskrain.aggregatornews.data.api.ServerDataSource;

/**
 * Created by Andrey Ievlev on 11,Май,2019
 */

public class NewDetailRepository implements INewsDetailRepository{
    private static final String NO_CONNECTION = "Нет подключения";
    private String urlNew;

    public NewDetailRepository(String urlNew) {
        this.urlNew = urlNew;
    }

    @Override
    public void getWebPage() {
//        if (NetworkStatus.isOnline()){
//            getData(urlNew);
//        } else {
//            callback.onMessageStatus(NO_CONNECTION);
//        }
    }


    public void getData(String urlNew) {
//        @SuppressLint("StaticFieldLeak") AsyncTask<String, String, String> loadRSSAsync = new AsyncTask<String, String, String>() {
//
//            @Override
//            protected String doInBackground(String... requestParameters) {
//                ServerDataSource serverDataSource = new ServerDataSource();
//                return serverDataSource.getHTTPData(requestParameters[0]);
//            }
//
//            @Override
//            protected void onPostExecute(String webPage) {
//                callback.onWebPage(webPage);
//            }
//        };
//        loadRSSAsync.execute(urlNew);
    }
}
