package com.olskrain.aggregatornews.presentationn.ui.view;

/**
 * Created by Andrey Ievlev on 03,Май,2019
 */

public interface IAllChannelsListView {
    void showLoading();

    void hideLoading();

    void displayMessages(String message);

    void refreshChannelsListRVAdapter();
}
