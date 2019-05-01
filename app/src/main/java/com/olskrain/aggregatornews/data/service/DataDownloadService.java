package com.olskrain.aggregatornews.data.service;

import android.app.IntentService;
import android.content.Intent;

import com.olskrain.aggregatornews.data.api.HTTPDataHandler;
import com.olskrain.aggregatornews.domain.entities.ChannelsList;

import timber.log.Timber;

public class DataDownloadService extends IntentService {

    public static final String ACTION_RESPONSE = "com.olskrain.aggregatornews.mvp.model.RESPONSE";
    public static final String EXTRA_KEY_OUT = "EXTRA_OUT";
    ChannelsList link;

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
        link = intent.getParcelableExtra("LinksList");
        HTTPDataHandler httpDataHandler = new HTTPDataHandler();
        //Todo: переделать для списка
        String responseServer = httpDataHandler.getHTTPData("https://news.yandex.ru/auto.rss");
        Timber.d("rty " + responseServer);

//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

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
