package com.olskrain.aggregatornews.presentationn.ui.view;

/**
 * Created by Andrey Ievlev on 11,Май,2019
 */

public interface INewDetailActivityView {
    void showLoading();

    void hideLoading();

    void goToNewsList();

    void sendWebPageData(String webPage);
}
