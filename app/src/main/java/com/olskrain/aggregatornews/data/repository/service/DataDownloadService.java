package com.olskrain.aggregatornews.data.repository.service;

import android.app.IntentService;
import android.content.Intent;

import com.olskrain.aggregatornews.Common.XmlRssParser;
import com.olskrain.aggregatornews.data.api.HTTPDataHandler;
import com.olskrain.aggregatornews.domain.entities.Channel;

import timber.log.Timber;

public class DataDownloadService extends IntentService {
    public static final String ACTION_RESPONSE = "com.olskrain.aggregatornews.mvp.model.RESPONSE";
    public static final String EXTRA_KEY_OUT = "EXTRA_OUT";
    private static final String DATA_DOWNLOAD_SERVICE = "DataDownloadService";

    public DataDownloadService() {
        super(DATA_DOWNLOAD_SERVICE);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.d("rty Запуск сервера");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String urlChannel = intent.getStringExtra("Url");
        HTTPDataHandler httpDataHandler = new HTTPDataHandler();
        String responseServer = httpDataHandler.getHTTPData(urlChannel);

        XmlRssParser xmlRssParser = new XmlRssParser();
        Channel channel = xmlRssParser.parseData(urlChannel, responseServer);

        Intent responseServerIntent = new Intent();
        responseServerIntent.setAction(ACTION_RESPONSE);
        responseServerIntent.addCategory(Intent.CATEGORY_DEFAULT); //Todo: посмотреть категории
        responseServerIntent.putExtra(EXTRA_KEY_OUT, channel);
        sendBroadcast(responseServerIntent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Timber.d("rty Остановка сервера");
    }
}
