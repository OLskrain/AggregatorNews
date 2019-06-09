package com.olskrain.aggregatornews.presentation.presenter.presenterNullCheck;

import com.olskrain.aggregatornews.Common.Command;
import com.olskrain.aggregatornews.domain.entities.ItemNew;
import com.olskrain.aggregatornews.presentation.presenter.DefaultPresenter;
import com.olskrain.aggregatornews.presentation.ui.view.INewsListFragmentView;

/**
 * Created by Andrey Ievlev on 09,Июнь,2019
 */

public abstract class NewsListFragmentPresenterNullCheck extends DefaultPresenter<INewsListFragmentView> {
    @Override
    public INewsListFragmentView createFakeView() {
        return new INewsListFragmentView() {
            @Override
            public void showLoading() {

            }

            @Override
            public void hideLoading() {

            }

            @Override
            public void goToNewsDetailActivity(String urlNews) {

            }

            @Override
            public void showBottomSheet(ItemNew itemNew) {

            }

            @Override
            public void showError(Command command) {

            }

            @Override
            public void refreshChannelsListRVAdapter() {

            }
        };
    }
}
