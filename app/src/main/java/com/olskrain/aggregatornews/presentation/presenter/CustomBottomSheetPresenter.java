package com.olskrain.aggregatornews.presentation.presenter;

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

    public void addFavorite (int channelPosition) {
        Timber.d("rty posddd " + channelPosition);
        customBottomSheetView.closeBottomSheet();
    }

    public void shareChannel(int channelPosition) {
        Timber.d("rty posddd " + channelPosition);
        customBottomSheetView.closeBottomSheet();
    }

    public void deleteChannel(int channelPosition) {
        Timber.d("rty posddd " + channelPosition);
        customBottomSheetView.closeBottomSheet();
    }

}
