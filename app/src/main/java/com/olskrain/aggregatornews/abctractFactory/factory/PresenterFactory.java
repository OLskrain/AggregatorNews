package com.olskrain.aggregatornews.abctractFactory.factory;

import com.olskrain.aggregatornews.abctractFactory.interfaceFactory.IPresenterFactory;
import com.olskrain.aggregatornews.presentation.presenter.AddChannelPresenter;
import com.olskrain.aggregatornews.presentation.presenter.BasePresenter;
import com.olskrain.aggregatornews.presentation.presenter.ChannelsListPresenter;
import com.olskrain.aggregatornews.presentation.presenter.CustomBottomSheetPresenter;
import com.olskrain.aggregatornews.presentation.presenter.FavoriteChannelsListPresenter;
import com.olskrain.aggregatornews.presentation.presenter.MainActivityPresenter;
import com.olskrain.aggregatornews.presentation.presenter.NewsDetailActivityPresenter;
import com.olskrain.aggregatornews.presentation.presenter.NewsListFragmentPresenter;
import com.olskrain.aggregatornews.presentation.presenter.SettingsPresenter;
import com.olskrain.aggregatornews.presentation.ui.view.IAddChannelView;
import com.olskrain.aggregatornews.presentation.ui.view.IBaseView;
import com.olskrain.aggregatornews.presentation.ui.view.IChannelsListView;
import com.olskrain.aggregatornews.presentation.ui.view.ICustomBottomSheetView;
import com.olskrain.aggregatornews.presentation.ui.view.IMainView;
import com.olskrain.aggregatornews.presentation.ui.view.INewsDetailActivityView;
import com.olskrain.aggregatornews.presentation.ui.view.INewsListFragmentView;
import com.olskrain.aggregatornews.presentation.ui.view.ISettingsView;

import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Andrey Ievlev on 29,Май,2019
 */

public class PresenterFactory implements IPresenterFactory {

    @Override
    public AddChannelPresenter createAddChannelPresenter(IAddChannelView addChannelView) {
        return new AddChannelPresenter(addChannelView);
    }

    @Override
    public ChannelsListPresenter createChannelsListPresenter(IChannelsListView view, CompositeDisposable compositeDisposable, Scheduler mainThreadScheduler) {
        return new ChannelsListPresenter(view, compositeDisposable, mainThreadScheduler);
    }

    @Override
    public CustomBottomSheetPresenter createCustomBottomSheetPresenter(ICustomBottomSheetView customBottomSheetView) {
        return new CustomBottomSheetPresenter(customBottomSheetView);
    }

    @Override
    public FavoriteChannelsListPresenter createFavoriteChannelsListPresenter() {
        return new FavoriteChannelsListPresenter();
    }

    @Override
    public MainActivityPresenter createMainActivityPresenter(IMainView view) {
        return new MainActivityPresenter(view);
    }

    @Override
    public NewsDetailActivityPresenter createNewsDetailActivityPresenter(INewsDetailActivityView view, CompositeDisposable compositeDisposable, Scheduler mainThreadScheduler) {
        return new NewsDetailActivityPresenter(view, compositeDisposable, mainThreadScheduler);
    }

    @Override
    public NewsListFragmentPresenter createNewsListFragmentPresenter(INewsListFragmentView view, CompositeDisposable compositeDisposable, Scheduler mainThreadScheduler) {
        return new NewsListFragmentPresenter(view, compositeDisposable, mainThreadScheduler);
    }

    @Override
    public SettingsPresenter createSettingsPresenter(ISettingsView view, CompositeDisposable compositeDisposable, Scheduler mainThreadScheduler) {
        return new SettingsPresenter(view, compositeDisposable, mainThreadScheduler);
    }

    @Override
    public BasePresenter createBasePresenter(IBaseView view, CompositeDisposable compositeDisposable, Scheduler mainThreadScheduler) {
        return new BasePresenter(view, compositeDisposable, mainThreadScheduler);
    }
}
