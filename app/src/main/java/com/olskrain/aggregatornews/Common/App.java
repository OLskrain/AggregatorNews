package com.olskrain.aggregatornews.Common;

import android.app.Application;

import com.olskrain.aggregatornews.data.cache.DBHelper;

import timber.log.Timber;

public class App extends Application {
    public static final String NAME_DB = "News";

    static private App instance;
    static private DBHelper dbHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        Timber.plant(new Timber.DebugTree());
        dbHelper = new DBHelper(instance, NAME_DB, null, 1);
    }

    public static App getInstance() {
        return instance;
    }

    public static DBHelper getDbHelper() {
        return dbHelper;
    }

}
