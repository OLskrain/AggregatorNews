package com.olskrain.aggregatornews.data.repository;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;

import com.olskrain.aggregatornews.Common.App;
import com.olskrain.aggregatornews.data.repository.interfaceRepository.IUrlChannelRepository;

import java.util.Objects;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Andrey Ievlev on 30,Май,2019
 */

public class UrlChannelRepository implements IUrlChannelRepository {

    private static final String URL_KEY = "url_key";
    private static final String DEFAULT_VALUE = "value";

    @Override
    public Single<String> getUrlChannel() {
        return Single.fromCallable(() -> Objects.requireNonNull(App.getInstance().getSharedPreferences().getString(URL_KEY, DEFAULT_VALUE)))
                .subscribeOn(Schedulers.io());
    }

    @SuppressLint("CommitPrefEdits")
    @Override
    public void putUrlChannel(String urlChannel) {
        SharedPreferences.Editor editor = App.getInstance().getSharedPreferences().edit();
        editor.putString(URL_KEY, urlChannel);
        editor.apply();
    }
}
