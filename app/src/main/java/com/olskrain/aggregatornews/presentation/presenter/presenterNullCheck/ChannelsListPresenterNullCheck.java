package com.olskrain.aggregatornews.presentation.presenter.presenterNullCheck;

import com.olskrain.aggregatornews.Common.Command;
import com.olskrain.aggregatornews.domain.entities.Feed;
import com.olskrain.aggregatornews.presentation.presenter.DefaultPresenter;
import com.olskrain.aggregatornews.presentation.ui.view.IChannelsListView;

import io.reactivex.Completable;

/**
 * Created by Andrey Ievlev on 09,Июнь,2019
 */

public abstract class ChannelsListPresenterNullCheck extends DefaultPresenter<IChannelsListView> {

    @Override
    public IChannelsListView createFakeView() {
        return new IChannelsListView() {
            @Override
            public void showLoading() {

            }

            @Override
            public void hideLoading() {

            }

            @Override
            public void showBottomSheet(Feed feed) {

            }

            @Override
            public Completable showWarning(Command command) {
                return null;
            }

            @Override
            public void showError(Command command) {

            }

            @Override
            public void refreshChannelsListRVAdapter() {

            }

            @Override
            public void goToChannelDetailFragment(String urlChannel) {

            }

            @Override
            public void goToAddChannelActivity() {

            }
        };
    }
}
