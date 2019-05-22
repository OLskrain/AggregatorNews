package com.olskrain.aggregatornews.presentation.ui.view;

import com.olskrain.aggregatornews.Common.Command;
import com.olskrain.aggregatornews.domain.entities.Feed;

import io.reactivex.Completable;

/**
 * Created by Andrey Ievlev on 03,Май,2019
 */

public interface IChannelsListView {

    void showLoading();

    void hideLoading();

    void goToChannelDetailFragment(String urlChannel);

    void showBottomSheet(Feed feed);

    Completable showWarning(Command command);

    void showError(Command command);

    void refreshChannelsListRVAdapter();
}
