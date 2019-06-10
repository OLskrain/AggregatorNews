package com.olskrain.aggregatornews.domain.usecase.interfaceUseCase;

import android.content.Context;

import io.reactivex.Completable;

/**
 * Created by Andrey Ievlev on 08,Июнь,2019
 */

public interface ISettingsUseCase {
    Completable saveAppTheme(int idRadioButton);

    Completable setLanguage(Context context, String language);

    void saveIndexRadioButton(int indexRadioButton);
}
