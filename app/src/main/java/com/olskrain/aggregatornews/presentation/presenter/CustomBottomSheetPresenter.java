package com.olskrain.aggregatornews.presentation.presenter;

import com.olskrain.aggregatornews.Common.Command;
import com.olskrain.aggregatornews.domain.entities.Feed;
import com.olskrain.aggregatornews.presentation.ui.view.ICustomBottomSheetView;

import timber.log.Timber;

/**
 * Created by Andrey Ievlev on 18,Май,2019
 */

public class CustomBottomSheetPresenter {
    private ICustomBottomSheetView customBottomSheetView;

    public CustomBottomSheetPresenter(ICustomBottomSheetView customBottomSheetView) {
        this.customBottomSheetView = customBottomSheetView;
    }

    public void addFavorite(Command command) {
        customBottomSheetView.closeBottomSheet();
    }

    public void shareChannel(Command command) {

        customBottomSheetView.closeBottomSheet();
    }

    public void deleteChannel(Command command) {
        customBottomSheetView.deleteChannel(command);
        customBottomSheetView.closeBottomSheet();
    }

    public void setChannelCard(Feed channel) {
        String title = channel.getTitle();
        String lastBuild = channel.getLastBuildDate();
        customBottomSheetView.setChannelCard(title, lastBuild);
    }
}
