package com.olskrain.aggregatornews.presentationn.ui.view;

/**
 * Created by Andrey Ievlev on 01,Май,2019
 */

public interface IMainView {

    void showLoading();

    void hideLoading();

    void displayMessages(String message);

    void refreshChannelsListRVAdapter();
}
