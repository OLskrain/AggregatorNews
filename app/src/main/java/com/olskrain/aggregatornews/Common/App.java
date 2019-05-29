package com.olskrain.aggregatornews.Common;

import android.app.Application;
import android.content.SharedPreferences;

import com.olskrain.aggregatornews.abctractFactory.FactoryProvider;
import com.olskrain.aggregatornews.data.cache.DBHelper;

import timber.log.Timber;

public class App extends Application {
    public static final String NAME_DB = "News";
    private static final String SHARED_PREFERENCES_TAG = "SPT";

    private static App instance;
    private DBHelper dbHelper;
    private SharedPreferences sharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        Timber.plant(new Timber.DebugTree());
        dbHelper = FactoryProvider.providerDBHelperFactory().createDBHelper(instance, NAME_DB, null, 1);
        sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_TAG, MODE_PRIVATE);
    }

    public static App getInstance() {
        return instance;
    }

    public DBHelper getDbHelper() {
        return dbHelper;
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

}
