package com.olskrain.aggregatornews.presentation.presenter.presenterNullCheck;

import com.olskrain.aggregatornews.presentation.presenter.DefaultPresenter;
import com.olskrain.aggregatornews.presentation.ui.view.IFavoriteChannelsListView;

/**
 * Created by Andrey Ievlev on 09,Июнь,2019
 */

public abstract class FavoriteChannelsListPresenterNullCheck extends DefaultPresenter<IFavoriteChannelsListView> {

    @Override
    public IFavoriteChannelsListView createFakeView() {
        return new IFavoriteChannelsListView() {};
    }
}
