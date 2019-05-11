package com.olskrain.aggregatornews.presentationn.ui.view;

/**
 * Created by Andrey Ievlev on 10,Май,2019
 */
public interface IChannelDetailFragmentView {
    void showLoading();

    void hideLoading();

    void refreshChannelsListRVAdapter();
}
