package com.olskrain.aggregatornews.data.repository;

import android.content.SharedPreferences;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.olskrain.aggregatornews.Common.App;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by Andrey Ievlev on 20,Май,2019
 */
public class UrlListRepositoryRepository implements IUrlsChannelListRepository {
    private static final String ERROR_SHARED_PREFERENCES = "Ошибка: нет URL списка";
    private static final String BUNCH_KEYS = "bunch keys";
    private static final String DEFAULT_VALUE = "value";
    private static final int DEFAULT_VALUE_KEYS = 50;

    @Override
    public Single<List<String>> getUrlsChannelList() {
        return Single.create(emitter -> {
            int bunchKeys = App.getInstance().getSharedPreferences().getInt(BUNCH_KEYS, DEFAULT_VALUE_KEYS);
            List<String> urlsList = new ArrayList<>();
            for (int i = 0; i < bunchKeys; i++) {
                String currentUrl = App.getInstance().getSharedPreferences().getString(Integer.toString(i), DEFAULT_VALUE);
                urlsList.add(currentUrl);
            }

            if (urlsList.isEmpty()) {
                emitter.onError(new RuntimeException(ERROR_SHARED_PREFERENCES));
            } else emitter.onSuccess(urlsList);
        }).subscribeOn(Schedulers.io()).cast((Class<List<String>>) (Class) List.class);
    }

//    public Completable deleteChannel(String urlChannel) {
//        return Completable.create(emitter -> {
//            try {
//                SQLiteDatabase connectDB = App.getInstance().getDbHelper().getWritableDatabase();
//                connectDB.execSQL("PRAGMA foreign_keys=ON");
//                connectDB.delete(TABLE_FEED, COLUMN_URL + " = ?", new String[]{urlChannel});
//                emitter.onComplete();
//            } catch (SQLException e) {
//                emitter.onError(new SQLException());
//            } finally {
//                App.getInstance().getDbHelper().close();
//            }
//        }).subscribeOn(Schedulers.io());
//    }
    @Override
    public void putUrlChannelsList(List<String> urlChannelsList) {
        SharedPreferences.Editor editor = App.getInstance().getSharedPreferences().edit();

        if (urlChannelsList != null) {
            editor.putInt(BUNCH_KEYS, urlChannelsList.size());
            editor.apply();

            for (int i = 0; i < urlChannelsList.size(); i++) {
                editor.putString(Integer.toString(i), urlChannelsList.get(i));
                editor.apply();
            }
        }
    }
}


