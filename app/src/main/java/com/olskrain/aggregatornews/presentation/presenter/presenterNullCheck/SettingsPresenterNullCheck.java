package com.olskrain.aggregatornews.presentation.presenter.presenterNullCheck;

import com.olskrain.aggregatornews.presentation.presenter.DefaultPresenter;
import com.olskrain.aggregatornews.presentation.ui.view.ISettingsView;

/**
 * Created by Andrey Ievlev on 09,Июнь,2019
 */

public abstract class SettingsPresenterNullCheck extends DefaultPresenter<ISettingsView> {

    @Override
    public ISettingsView createFakeView() {
        return new ISettingsView() {
            @Override
            public void setAppTheme() {

            }
        };
    }
}
