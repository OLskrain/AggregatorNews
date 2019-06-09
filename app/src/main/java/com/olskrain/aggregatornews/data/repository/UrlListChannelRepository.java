package com.olskrain.aggregatornews.data.repository;

import android.content.SharedPreferences;

import com.olskrain.aggregatornews.Common.App;
import com.olskrain.aggregatornews.data.repository.interfaceRepository.IUrlsChannelListRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Andrey Ievlev on 20,Май,2019
 */

public class UrlListChannelRepository implements IUrlsChannelListRepository {
    private static final String BUNCH_KEYS = "bunch keys";
    private static final String DEFAULT_VALUE = "value";
    private static final int DEFAULT_VALUE_KEYS = 50;

    @Override
    public Single<List<String>> getUrlsChannelList() {
        return Single.fromCallable(() -> {
            int bunchKeys = App.getInstance().getSharedPreferences().getInt(BUNCH_KEYS, DEFAULT_VALUE_KEYS);
            List<String> urlsList = new ArrayList<>();
            for (int i = 0; i < bunchKeys; i++) {
                String currentUrl = App.getInstance().getSharedPreferences().getString(Integer.toString(i), DEFAULT_VALUE);
                urlsList.add(currentUrl);
            }
            return urlsList;
        }).subscribeOn(Schedulers.io());
    }

    @Override
    public void putUrlsChannelsList(final List<String> urlChannelsList) {
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


