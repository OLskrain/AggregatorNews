package com.olskrain.aggregatornews.presentation.presenter.presenterNullCheck;

import com.olskrain.aggregatornews.presentation.presenter.DefaultPresenter;
import com.olskrain.aggregatornews.presentation.ui.view.IMainView;

/**
 * Created by Andrey Ievlev on 09,Июнь,2019
 */
public abstract class MainActivityPresenterNullCheck extends DefaultPresenter<IMainView> {

    @Override
    public IMainView createFakeView() {
        return new IMainView() {
            @Override
            public void goToSettingsActivity() {

            }
        };
    }
}
