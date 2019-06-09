package com.olskrain.aggregatornews.presentation.presenter.presenterNullCheck;

import com.olskrain.aggregatornews.Common.Command;
import com.olskrain.aggregatornews.presentation.presenter.DefaultPresenter;
import com.olskrain.aggregatornews.presentation.ui.view.ICustomBottomSheetView;

/**
 * Created by Andrey Ievlev on 09,Июнь,2019
 */

public abstract class CustomBottomSheetPresenterNullCheck extends DefaultPresenter<ICustomBottomSheetView> {

    @Override
    public ICustomBottomSheetView createFakeView() {
        return new ICustomBottomSheetView() {
            @Override
            public void setChannelCard(String title, String LastBuildDate) {

            }

            @Override
            public void addFavorite(Command command) {

            }

            @Override
            public void shareChannel(Command command) {

            }

            @Override
            public void deleteChannel(Command command) {

            }

            @Override
            public void closeBottomSheet() {

            }
        };
    }
}
