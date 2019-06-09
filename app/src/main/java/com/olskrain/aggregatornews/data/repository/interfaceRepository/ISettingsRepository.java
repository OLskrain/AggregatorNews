package com.olskrain.aggregatornews.data.repository.interfaceRepository;

import io.reactivex.Completable;

/**
 * Created by Andrey Ievlev on 08,Июнь,2019
 */

public interface ISettingsRepository {
    Completable saveAppTheme(int idRadioButton, boolean defaultThemeStatus, boolean blackThemeStatus, boolean purpleThemeStatus);

    void saveIndexRadioButton(int indexRadioButton);
}
