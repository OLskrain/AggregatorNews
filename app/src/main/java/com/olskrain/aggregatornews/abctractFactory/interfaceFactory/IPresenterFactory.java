package com.olskrain.aggregatornews.abctractFactory.interfaceFactory;

import com.olskrain.aggregatornews.presentation.presenter.AddChannelPresenter;
import com.olskrain.aggregatornews.presentation.presenter.ChannelsListPresenter;
import com.olskrain.aggregatornews.presentation.presenter.CustomBottomSheetPresenter;
import com.olskrain.aggregatornews.presentation.presenter.FavoriteChannelsListPresenter;
import com.olskrain.aggregatornews.presentation.presenter.MainActivityPresenter;
import com.olskrain.aggregatornews.presentation.presenter.NewsDetailActivityPresenter;
import com.olskrain.aggregatornews.presentation.presenter.NewsListFragmentPresenter;
import com.olskrain.aggregatornews.presentation.presenter.OtherPresenter;
import com.olskrain.aggregatornews.presentation.ui.view.IAddChannelView;
import com.olskrain.aggregatornews.presentation.ui.view.IChannelDetailFragmentView;
import com.olskrain.aggregatornews.presentation.ui.view.IChannelsListView;
import com.olskrain.aggregatornews.presentation.ui.view.ICustomBottomSheetView;
import com.olskrain.aggregatornews.presentation.ui.view.IMainView;
import com.olskrain.aggregatornews.presentation.ui.view.INewDetailActivityView;

import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Andrey Ievlev on 29,Май,2019
 */

public interface IPresenterFactory {
    AddChannelPresenter createAddChannelPresenter(IAddChannelView addChannelView);

    ChannelsListPresenter createChannelsListPresenter(IChannelsListView view, CompositeDisposable compositeDisposable, Scheduler mainThreadScheduler);

    CustomBottomSheetPresenter createCustomBottomSheetPresenter(ICustomBottomSheetView customBottomSheetView);

    FavoriteChannelsListPresenter createFavoriteChannelsListPresenter();

    MainActivityPresenter createMainActivityPresenter(IMainView view);

    NewsDetailActivityPresenter createNewsDetailActivityPresenter(INewDetailActivityView view, CompositeDisposable compositeDisposable, Scheduler mainThreadScheduler);

    NewsListFragmentPresenter createNewsListFragmentPresenter(IChannelDetailFragmentView view, CompositeDisposable compositeDisposable, Scheduler mainThreadScheduler);

    OtherPresenter createOtherPresenter();
}
