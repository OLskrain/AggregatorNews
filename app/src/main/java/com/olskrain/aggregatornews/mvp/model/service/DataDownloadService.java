package com.olskrain.aggregatornews.mvp.model.service;

import android.app.IntentService;
import android.content.Intent;

import com.olskrain.aggregatornews.mvp.model.api.HTTPDataHandler;

import timber.log.Timber;

public class DataDownloadService extends IntentService {

    public static final String ACTION_RESPONSE = "com.olskrain.aggregatornews.mvp.model.RESPONSE";
    public static final String EXTRA_KEY_OUT = "EXTRA_OUT";

//    public static final String ACTION_UPDATE = "ru.alexanderklimov.intentservice.UPDATE";
//    public static final String EXTRA_KEY_UPDATE = "EXTRA_UPDATE";

    public DataDownloadService() {
        super("DataDownloadService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.d("rty Запуск сервера");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String link = intent.getStringExtra("RssLinkList");
        HTTPDataHandler httpDataHandler = new HTTPDataHandler();
        String responseServer = httpDataHandler.getHTTPData(link);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        // возвращаем результат
        Intent responseIntent = new Intent();
        responseIntent.setAction(ACTION_RESPONSE);
        responseIntent.addCategory(Intent.CATEGORY_DEFAULT); //Todo: посмотреть категории
        responseIntent.putExtra(EXTRA_KEY_OUT, responseServer);
        sendBroadcast(responseIntent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Timber.d("rty Остановка сервера");
    }
}
