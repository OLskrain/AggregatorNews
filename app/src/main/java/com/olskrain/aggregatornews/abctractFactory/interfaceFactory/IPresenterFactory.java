package com.olskrain.aggregatornews.abctractFactory.interfaceFactory;

import com.olskrain.aggregatornews.presentation.presenter.ChannelsListPresenter;
import com.olskrain.aggregatornews.presentation.presenter.NewsListFragmentPresenter;
import com.olskrain.aggregatornews.presentation.presenter.interfacePresenter.IAddChannelPresenter;
import com.olskrain.aggregatornews.presentation.presenter.interfacePresenter.IBasePresenter;
import com.olskrain.aggregatornews.presentation.presenter.interfacePresenter.ICustomBottomSheetPresenter;
import com.olskrain.aggregatornews.presentation.presenter.interfacePresenter.IFavoriteChannelsListPresenter;
import com.olskrain.aggregatornews.presentation.presenter.interfacePresenter.IMainActivityPresenter;
import com.olskrain.aggregatornews.presentation.presenter.interfacePresenter.INewsDetailActivityPresenter;
import com.olskrain.aggregatornews.presentation.presenter.interfacePresenter.ISettingsPresenter;
import com.olskrain.aggregatornews.presentation.ui.view.IAddChannelView;
import com.olskrain.aggregatornews.presentation.ui.view.IBaseView;
import com.olskrain.aggregatornews.presentation.ui.view.IChannelDetailFragmentView;
import com.olskrain.aggregatornews.presentation.ui.view.IChannelsListView;
import com.olskrain.aggregatornews.presentation.ui.view.ICustomBottomSheetView;
import com.olskrain.aggregatornews.presentation.ui.view.IMainView;
import com.olskrain.aggregatornews.presentation.ui.view.INewDetailActivityView;
import com.olskrain.aggregatornews.presentation.ui.view.ISettingsView;

import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Andrey Ievlev on 29,Май,2019
 */

public interface IPresenterFactory {
    IAddChannelPresenter createAddChannelPresenter(IAddChannelView addChannelView);

    ChannelsListPresenter createChannelsListPresenter(IChannelsListView view, CompositeDisposable compositeDisposable, Scheduler mainThreadScheduler);

    ICustomBottomSheetPresenter createCustomBottomSheetPresenter(ICustomBottomSheetView customBottomSheetView);

    IFavoriteChannelsListPresenter createFavoriteChannelsListPresenter();

    IMainActivityPresenter createMainActivityPresenter(IMainView view);

    INewsDetailActivityPresenter createNewsDetailActivityPresenter(INewDetailActivityView view, CompositeDisposable compositeDisposable, Scheduler mainThreadScheduler);

    NewsListFragmentPresenter createNewsListFragmentPresenter(IChannelDetailFragmentView view, CompositeDisposable compositeDisposable, Scheduler mainThreadScheduler);

    ISettingsPresenter createSettingsPresenter(ISettingsView view, CompositeDisposable compositeDisposable, Scheduler mainThreadScheduler);

    IBasePresenter createBasePresenter(IBaseView view, CompositeDisposable compositeDisposable, Scheduler mainThreadScheduler);
}
