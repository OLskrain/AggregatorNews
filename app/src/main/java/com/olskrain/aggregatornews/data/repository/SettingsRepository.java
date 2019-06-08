package com.olskrain.aggregatornews.data.repository;

import com.olskrain.aggregatornews.data.cache.SettingsSharedPref;
import com.olskrain.aggregatornews.data.repository.interfaceRepositiry.ISettingsRepository;

import io.reactivex.Completable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Andrey Ievlev on 08,Июнь,2019
 */

public class SettingsRepository implements ISettingsRepository {

    @Override
    public Completable saveAppTheme(final int idRadioButton, final boolean defaultThemeStatus,
                                    final boolean blackThemeStatus, final boolean purpleThemeStatus) {
        return Completable.fromAction(() -> {
            putAppTheme(defaultThemeStatus, blackThemeStatus, purpleThemeStatus);

        }).subscribeOn(Schedulers.io());
    }

    @Override
    public void saveIndexRadioButton(int indexRadioButton) {
        SettingsSharedPref.getInstance().putIndexRadioButton(indexRadioButton);
    }

    private void putAppTheme(final boolean defaultThemeStatus, final boolean blackThemeStatus, final boolean purpleThemeStatus) {
        SettingsSharedPref.getInstance().putDefaultThemeStatus(defaultThemeStatus);
        SettingsSharedPref.getInstance().putBlackThemeStatus(blackThemeStatus);
        SettingsSharedPref.getInstance().putPurpleThemeStatus(purpleThemeStatus);
    }
}
