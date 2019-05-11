package com.olskrain.aggregatornews.presentationn.presenter;

import com.olskrain.aggregatornews.presentationn.ui.view.IChannelDetailActivityView;

/**
 * Created by Andrey Ievlev on 03,Май,2019
 */

public class ChannelDetailActivityPresenter {

    private IChannelDetailActivityView iChannelDetailView;

    public ChannelDetailActivityPresenter(IChannelDetailActivityView view) {
        this.iChannelDetailView = view;
    }

    public void doToAllChannelsList() {
        iChannelDetailView.goToAllChannelsList();
    }
}

