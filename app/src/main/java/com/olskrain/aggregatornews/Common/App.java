package com.olskrain.aggregatornews.Common;

import android.app.Application;
import android.content.SharedPreferences;

import com.olskrain.aggregatornews.data.cache.DBHelper;
import com.olskrain.aggregatornews.data.repository.ResponseServiceBroadcast;

import timber.log.Timber;

public class App extends Application {
    public static final String NAME_DB = "News";
    private static final String SHARED_PREFERENCES_TAG = "SPT";

    static private App instance;
    static private DBHelper dbHelper;
    static private SharedPreferences sharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        Timber.plant(new Timber.DebugTree());
        dbHelper = new DBHelper(instance, NAME_DB, null, 1);
        sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_TAG, MODE_PRIVATE);
    }

    public static App getInstance() {
        return instance;
    }

    public static DBHelper getDbHelper() {
        return dbHelper;
    }

    public static SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

}
