package com.olskrain.aggregatornews.presentation.presenter;

import com.olskrain.aggregatornews.presentation.presenter.interfacePresenter.IMainActivityPresenter;
import com.olskrain.aggregatornews.presentation.presenter.presenterNullCheck.MainActivityPresenterNullCheck;
import com.olskrain.aggregatornews.presentation.ui.view.IMainView;

/**
 * Created by Andrey Ievlev on 22,Апрель,2019
 */

public class MainActivityPresenter extends MainActivityPresenterNullCheck implements IMainActivityPresenter {

    private final IMainView mainView;

    public MainActivityPresenter(final IMainView view) {
        this.mainView = view;
    }

    @Override
    public void goToSettingsActivity() {
        getView().goToSettingsActivity();
    }
}
