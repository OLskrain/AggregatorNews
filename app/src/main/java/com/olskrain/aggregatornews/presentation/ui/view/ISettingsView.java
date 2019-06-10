package com.olskrain.aggregatornews.presentation.ui.view;

import com.olskrain.aggregatornews.Common.Command;

/**
 * Created by Andrey Ievlev on 06,Июнь,2019
 */

public interface ISettingsView {
    void restartActivity();
    void showDialog(Command command);
}
