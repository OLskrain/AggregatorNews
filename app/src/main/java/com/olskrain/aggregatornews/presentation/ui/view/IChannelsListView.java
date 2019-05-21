package com.olskrain.aggregatornews.presentation.ui.view;

import com.olskrain.aggregatornews.Common.Command;
import com.olskrain.aggregatornews.domain.entities.Feed;

/**
 * Created by Andrey Ievlev on 03,Май,2019
 */

public interface IChannelsListView {

    void showLoading();

    void hideLoading();

    void goToChannelDetailFragment(String urlChannel);

    void showBottomSheet(Feed feed);

    void showError(Command command);

    void displayMessages(String message);

    void refreshChannelsListRVAdapter();
}
