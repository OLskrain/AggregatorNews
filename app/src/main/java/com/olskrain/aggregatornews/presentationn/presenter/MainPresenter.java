package com.olskrain.aggregatornews.presentationn.presenter;

import com.olskrain.aggregatornews.presentationn.ui.view.IMainView;

/**
 * Created by Andrey Ievlev on 22,Апрель,2019
 */

public class MainPresenter {
    private IMainView mainView;

    public MainPresenter(IMainView view) {
        this.mainView = view;
    }

    public void goToFragment(int buttonID) {
        mainView.goToFragment(buttonID);
    }

}
