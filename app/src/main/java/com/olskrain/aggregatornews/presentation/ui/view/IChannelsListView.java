package com.olskrain.aggregatornews.presentation.ui.view;

import com.olskrain.aggregatornews.Common.Command;

/**
 * Created by Andrey Ievlev on 03,Май,2019
 */

public interface IChannelsListView {

    void showLoading();

    void hideLoading();

    void goToChannelDetailFragment(int position);

    void showBottomSheet(int position);

    void showError(Command command);

    void displayMessages(String message);

    void refreshChannelsListRVAdapter();
}
