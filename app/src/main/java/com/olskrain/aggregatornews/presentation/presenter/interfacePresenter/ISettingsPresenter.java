package com.olskrain.aggregatornews.presentation.presenter.interfacePresenter;

import android.content.Context;

import com.olskrain.aggregatornews.Common.Command;

/**
 * Created by Andrey Ievlev on 06,Июнь,2019
 */

public interface ISettingsPresenter {
    void saveAppTheme(int idRadioButton);

    void saveIndexRadioButton(int indexRadioButton);

    void setLanguage(Context context, String language);

    void showDialog(Command command);
}
