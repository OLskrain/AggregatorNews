package com.olskrain.aggregatornews.presentation.ui.view;

/**
 * Created by Andrey Ievlev on 03,Май,2019
 */

public interface IChannelsListView {

    void showLoading();

    void hideLoading();

    void goToChannelDetailFragment(int position);

    void showBottomSheet(int position);

    void displayMessages(String message);

    void refreshChannelsListRVAdapter();
}
