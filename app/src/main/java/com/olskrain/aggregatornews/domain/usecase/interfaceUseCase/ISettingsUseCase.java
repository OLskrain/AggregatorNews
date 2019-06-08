package com.olskrain.aggregatornews.domain.usecase.interfaceUseCase;

import io.reactivex.Completable;

/**
 * Created by Andrey Ievlev on 08,Июнь,2019
 */

public interface ISettingsUseCase {
    Completable saveAppTheme(int idRadioButton);

    void saveIndexRadioButton(int indexRadioButton);
}
