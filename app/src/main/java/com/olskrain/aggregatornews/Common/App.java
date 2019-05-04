package com.olskrain.aggregatornews.Common;

import android.app.Application;
import android.content.Intent;
import android.content.IntentFilter;

import com.olskrain.aggregatornews.data.cache.DBHelper;
import com.olskrain.aggregatornews.data.repository.ResponseServiceBroadcast;
import com.olskrain.aggregatornews.data.repository.service.DataDownloadService;

import timber.log.Timber;

public class App extends Application {
    public static final String NAME_DB = "News";

    static private App instance;
    static private DBHelper dbHelper;
    static private ResponseServiceBroadcast responseService;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        Timber.plant(new Timber.DebugTree());
        dbHelper = new DBHelper(instance, NAME_DB, null, 1);

        responseService = new ResponseServiceBroadcast();
        IntentFilter intentFilter = new IntentFilter(DataDownloadService.ACTION_RESPONSE);
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(responseService, intentFilter);

    }

    public static App getInstance() {
        return instance;
    }

    public static DBHelper getDbHelper() {
        return dbHelper;
    }

    public static ResponseServiceBroadcast getResponseServiceBroadcast() {
        return responseService;
    }
}
