package com.olskrain.aggregatornews.Common;

import android.app.Application;
import android.content.SharedPreferences;

import com.olskrain.aggregatornews.data.cache.DBHelper;

import io.reactivex.disposables.CompositeDisposable;
import timber.log.Timber;

public class App extends Application {
    public static final String NAME_DB = "News";
    private static final String SHARED_PREFERENCES_TAG = "SPT";

    private static App instance;
    private DBHelper dbHelper;
    private SharedPreferences sharedPreferences;
    private static CompositeDisposable compositeDisposable;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        compositeDisposable = new CompositeDisposable();

        Timber.plant(new Timber.DebugTree());
        dbHelper = new DBHelper(instance, NAME_DB, null, 1);
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

    public CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }
}
