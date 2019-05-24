package com.olskrain.aggregatornews.presentation.ui.view;

/**
 * Created by Andrey Ievlev on 11,Май,2019
 */

public interface INewDetailActivityView {
    void showLoading();

    void hideLoading();

    void showError();

    void goToNewsList();

    void sendWebPageData(String webPage);
}
