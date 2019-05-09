package com.olskrain.aggregatornews.data.repository.service;

import android.app.IntentService;
import android.content.Intent;

import com.olskrain.aggregatornews.Common.XmlRssParser;
import com.olskrain.aggregatornews.data.api.HTTPDataHandler;
import com.olskrain.aggregatornews.domain.entities.Channel;

public class DataDownloadService extends IntentService {
    public static final String ACTION_RESPONSE = "com.olskrain.aggregatornews.mvp.model.RESPONSE";
    public static final String EXTRA_KEY_OUT = "extra key out";
    private static final String DATA_DOWNLOAD_SERVICE = "DataDownloadService";
    private static final String EXTRA_KEY = "extra key";

    public DataDownloadService() {
        super(DATA_DOWNLOAD_SERVICE);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String[] urlsChannel = intent.getStringArrayExtra(EXTRA_KEY);
        HTTPDataHandler httpDataHandler = new HTTPDataHandler();
        XmlRssParser xmlRssParser = new XmlRssParser();

        for (int i = 0; i < urlsChannel.length; i++) {
            String responseServer = httpDataHandler.getHTTPData(urlsChannel[i]);
            Channel channel = xmlRssParser.parseData(urlsChannel[i], responseServer);

            Intent responseServerIntent = new Intent();
            responseServerIntent.setAction(ACTION_RESPONSE);
            responseServerIntent.addCategory(Intent.CATEGORY_DEFAULT); //Todo: посмотреть категории
            responseServerIntent.putExtra(EXTRA_KEY_OUT, channel);
            sendBroadcast(responseServerIntent);
        }

    }
}
