package com.olskrain.aggregatornews.presentation.ui.view;

import com.olskrain.aggregatornews.Common.Command;
import com.olskrain.aggregatornews.domain.entities.ItemNew;

/**
 * Created by Andrey Ievlev on 10,Май,2019
 */
public interface IChannelDetailFragmentView {
    void showLoading();

    void hideLoading();

    void showBottomSheet(ItemNew itemNew);

    void showError(Command command);

    void refreshChannelsListRVAdapter();
}
