package com.olskrain.aggregatornews.presentation.ui.view;

import com.olskrain.aggregatornews.Common.Command;

/**
 * Created by Andrey Ievlev on 18,Май,2019
 */

public interface ICustomBottomSheetView {
    void setChannelCard(String title, String LastBuildDate);

    void addFavorite(Command command);

    void shareChannel(Command command);

    void deleteChannel(Command command);

    void closeBottomSheet();

}
