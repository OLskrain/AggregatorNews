package com.olskrain.aggregatornews.presentation.presenter;

import com.olskrain.aggregatornews.presentation.ui.view.IMainView;

/**
 * Created by Andrey Ievlev on 22,Апрель,2019
 */

public class MainActivityPresenter {
    private IMainView mainView;

    public MainActivityPresenter(IMainView view) {
        this.mainView = view;
    }

    public void goToFragment() {
        mainView.goToSettingsActivity();
    }
}
