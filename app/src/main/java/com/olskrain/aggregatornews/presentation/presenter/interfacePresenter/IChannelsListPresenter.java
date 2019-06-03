package com.olskrain.aggregatornews.presentation.presenter.interfacePresenter;

import com.olskrain.aggregatornews.Common.Command;

/**
 * Created by Andrey Ievlev on 03,Июнь,2019
 */

public interface IChannelsListPresenter {
    void attachView();
    void checkDuplicate(String urlChannel);
    void showDeletionWarning(Command command);
    void deleteAllChannels();
    void getUrlsChannelList();
    void refreshChannelsList();
    void saveUrlsChannelList();
    void goToAddChannelActivity();
}
