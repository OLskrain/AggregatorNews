//package com.olskrain.aggregatornews.data.repository.service;
//
//import android.app.IntentService;
//import android.content.Intent;
//
//import com.olskrain.aggregatornews.Common.XmlRssParser;
//import com.olskrain.aggregatornews.data.api.HTTPDataHandler;
//import com.olskrain.aggregatornews.domain.entities.Channel;
//
//public class DataDownloadService extends IntentService {
//    public static final String ACTION_RESPONSE = "com.olskrain.aggregatornews.mvp.model.RESPONSE";
//    public static final String EXTRA_KEY_OUT = "extra key out";
//    private static final String DATA_DOWNLOAD_SERVICE = "DataDownloadService";
//    private static final String EXTRA_KEY = "extra key";
//
//    public DataDownloadService() {
//        super(DATA_DOWNLOAD_SERVICE);
//    }
//
//    @Override
//    protected void onHandleIntent(Intent intent) {
//        String[] urlsChannel = intent.getStringArrayExtra(EXTRA_KEY);
//        HTTPDataHandler httpDataHandler = new HTTPDataHandler();
//        XmlRssParser xmlRssParser = new XmlRssParser();
//
//        for (final String anUrlsChannel : urlsChannel) {
//            String responseServer = httpDataHandler.getHTTPData(anUrlsChannel);
//            Channel channel = xmlRssParser.parseData(anUrlsChannel, responseServer);
//
//            Intent responseServerIntent = new Intent();
//            responseServerIntent.setAction(ACTION_RESPONSE);
//            responseServerIntent.addCategory(Intent.CATEGORY_DEFAULT);
//            responseServerIntent.putExtra(EXTRA_KEY_OUT, channel);
//            sendBroadcast(responseServerIntent);
//        }
//
//    }
//}
