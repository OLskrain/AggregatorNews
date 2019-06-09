package com.olskrain.aggregatornews.presentation.presenter.presenterNullCheck;

import com.olskrain.aggregatornews.presentation.presenter.DefaultPresenter;
import com.olskrain.aggregatornews.presentation.ui.view.INewsDetailActivityView;

/**
 * Created by Andrey Ievlev on 09,Июнь,2019
 */

public abstract class NewsDetailActivityPresenterNullCheck extends DefaultPresenter<INewsDetailActivityView> {
    @Override
    public INewsDetailActivityView createFakeView() {
        return new INewsDetailActivityView() {
            @Override
            public void showLoading() {

            }

            @Override
            public void hideLoading() {

            }

            @Override
            public void showError() {

            }

            @Override
            public void goToNewsList() {

            }

            @Override
            public void sendWebPageData(String webPage) {

            }
        };
    }
}
