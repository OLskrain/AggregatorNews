package com.olskrain.aggregatornews.data.repository;

import com.olskrain.aggregatornews.R;
import com.olskrain.aggregatornews.data.cache.SettingsSharedPref;
import com.olskrain.aggregatornews.data.repository.interfaceRepository.IBaseRepository;

import io.reactivex.Single;

/**
 * Created by Andrey Ievlev on 09,Июнь,2019
 */

public class BaseRepository implements IBaseRepository {

    @Override
    public Single<Integer> getAppTheme() {
        return Single.fromCallable(() -> {
            boolean blackThemeEnabled = SettingsSharedPref.getInstance().getBlackTheme();
            boolean purpleThemeEnabled = SettingsSharedPref.getInstance().getPurpleTheme();

            if (blackThemeEnabled) {
                return R.style.MyTheme_Black;
            } else if (purpleThemeEnabled) {
                return R.style.MyTheme_Purple;
            } else {
                return R.style.MyTheme;
            }
        });
    }
}
