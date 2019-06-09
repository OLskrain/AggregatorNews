package com.olskrain.aggregatornews.presentation.presenter.presenterNullCheck;

import com.olskrain.aggregatornews.presentation.presenter.DefaultPresenter;
import com.olskrain.aggregatornews.presentation.ui.view.IAddChannelView;

/**
 * Created by Andrey Ievlev on 09,Июнь,2019
 */

public abstract class AddChannelPresenterNullCheck extends DefaultPresenter<IAddChannelView> {

    @Override
    public IAddChannelView createFakeView() {
        return new IAddChannelView() {
            @Override
            public void goToAllChannelList() {

            }

            @Override
            public void showError() {

            }

            @Override
            public void hideError() {

            }
        };
    }
}
