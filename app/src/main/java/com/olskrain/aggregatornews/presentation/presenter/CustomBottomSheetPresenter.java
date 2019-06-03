package com.olskrain.aggregatornews.presentation.presenter;

import com.olskrain.aggregatornews.Common.Command;
import com.olskrain.aggregatornews.domain.entities.Feed;
import com.olskrain.aggregatornews.presentation.presenter.interfacePresenter.ICustomBottomSheetPresenter;
import com.olskrain.aggregatornews.presentation.ui.view.ICustomBottomSheetView;

import timber.log.Timber;

/**
 * Created by Andrey Ievlev on 18,Май,2019
 */

public class CustomBottomSheetPresenter implements ICustomBottomSheetPresenter {
    private final ICustomBottomSheetView customBottomSheetView;

    public CustomBottomSheetPresenter(final ICustomBottomSheetView customBottomSheetView) {
        this.customBottomSheetView = customBottomSheetView;
    }

    @Override
    public void addFavorite(final Command command) {
        customBottomSheetView.addFavorite(command);
        customBottomSheetView.closeBottomSheet();
    }

    @Override
    public void shareChannel(final Command command) {
        customBottomSheetView.shareChannel(command);
        customBottomSheetView.closeBottomSheet();
    }

    @Override
    public void deleteChannel(final Command command) {
        customBottomSheetView.deleteChannel(command);
        customBottomSheetView.closeBottomSheet();
    }

    @Override
    public void setChannelCard(final Feed channel) {
        String title = channel.getTitle();
        String lastBuild = channel.getLastBuildDate();
        customBottomSheetView.setChannelCard(title, lastBuild);
    }
}
