package com.olskrain.aggregatornews.presentation.presenter.interfacePresenter;

import com.olskrain.aggregatornews.Common.Command;
import com.olskrain.aggregatornews.domain.entities.Feed;

/**
 * Created by Andrey Ievlev on 03,Июнь,2019
 */

public interface ICustomBottomSheetPresenter {
    void addFavorite(Command command);
    void shareChannel(Command command);
    void deleteChannel(Command command);
    void setChannelCard(Feed channel);
}
