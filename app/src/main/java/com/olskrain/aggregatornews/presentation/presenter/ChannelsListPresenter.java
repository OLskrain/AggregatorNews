package com.olskrain.aggregatornews.presentation.presenter;

import android.annotation.SuppressLint;

import com.olskrain.aggregatornews.Common.App;
import com.olskrain.aggregatornews.Common.Command;
import com.olskrain.aggregatornews.domain.entities.Feed;
import com.olskrain.aggregatornews.domain.usecase.ChannelsListUseCase;
import com.olskrain.aggregatornews.domain.usecase.IChannelsListUseCase;
import com.olskrain.aggregatornews.domain.usecase.IUrlsChannelListUseCase;
import com.olskrain.aggregatornews.domain.usecase.UrlsChannelListUseCase;
import com.olskrain.aggregatornews.presentation.presenter.list.IChannelListPresenter;
import com.olskrain.aggregatornews.presentation.ui.view.IChannelsListView;
import com.olskrain.aggregatornews.presentation.ui.view.item.IChannelListItemView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;
import timber.log.Timber;

/**
 * Created by Andrey Ievlev on 03,Май,2019
 */

public class ChannelsListPresenter {
    public class ChannelListPresenter implements IChannelListPresenter {
        private final PublishSubject<IChannelListItemView> clickItem = PublishSubject.create();
        private final PublishSubject<IChannelListItemView> clickMenu = PublishSubject.create();

        @Override
        public PublishSubject<IChannelListItemView> getClickOnItem() {
            return clickItem;
        }

        @Override
        public PublishSubject<IChannelListItemView> getClickOnMenu() {
            return clickMenu;
        }

        @Override
        public void bindView(IChannelListItemView rowView) {
            String channelTitle = channelsListLocal.get(rowView.getPos()).getTitle();
            String lastBuildDate = channelsListLocal.get(rowView.getPos()).getLastBuildDate();
            rowView.setTitle(channelTitle);
            rowView.setLastBuildDate(lastBuildDate);
        }

        @Override
        public int getChannelCount() {
            return channelsListLocal == null ? 0 : channelsListLocal.size();
        }
    }

    public ChannelListPresenter channelListPresenter;
    private IChannelsListView channelsListView;
    private IChannelsListUseCase channelsListUseCase;
    private IUrlsChannelListUseCase urlsChannelListUseCase;
    private Scheduler mainThreadScheduler;
    private List<Feed> channelsListLocal = new ArrayList<>();
    private List<String> urlChannelsListLocal = new ArrayList<>();
    private Disposable disposable;

    public ChannelsListPresenter(IChannelsListView view, Scheduler mainThreadScheduler) {
        this.channelsListView = view;
        this.mainThreadScheduler = mainThreadScheduler;
        this.channelsListUseCase = new ChannelsListUseCase();
        this.channelListPresenter = new ChannelListPresenter();
        this.urlsChannelListUseCase = new UrlsChannelListUseCase();
    }

    @SuppressLint("CheckResult")
    public void attachView() {
        channelListPresenter.clickItem.subscribe(iChannelListItemView ->
                channelsListView.goToChannelDetailFragment(channelsListLocal.get(iChannelListItemView.getCurrentPosition()).getUrl()));

        channelListPresenter.clickMenu.subscribe(iChannelListItemView ->
                channelsListView.showBottomSheet(channelsListLocal.get(iChannelListItemView.getCurrentPosition())));
    }

    @SuppressLint("CheckResult")
    public void addNewChannel(String urlChannel) {
       if(channelsListUseCase.checkDuplicate(urlChannel, urlChannelsListLocal)) {
           channelsListView.showLoading();
           Single<Feed> responseRepository = channelsListUseCase.addNewChannel(Command.ADD_CHANNEL, urlChannel);

           disposable = responseRepository
                   .observeOn(mainThreadScheduler)
                   .subscribe(channel -> {
                       channelsListLocal.add(channel);
                       updateUrlsChannelList(channelsListLocal);
                       channelsListView.hideLoading();
                       channelsListView.refreshChannelsListRVAdapter();
                   }, throwable -> {
                       channelsListView.hideLoading();
                       channelsListView.showError(Command.ADD_CHANNEL);
                   });

           App.getInstance().getCompositeDisposable().add(disposable);
       }
    }

    //Todo: потом перенести
    public void deleteAllChannels() {
        channelsListLocal = channelsListUseCase.deleteAllChannels(Command.DELETE_ALL_CHANNELS, channelsListLocal);
        updateUrlsChannelList(channelsListLocal);
        channelsListView.refreshChannelsListRVAdapter();
    }

    public void refreshChannelsList() {
        channelsListView.showLoading();
        Single<List<String>> responseRepository = urlsChannelListUseCase.getUrlsChannelList();

        disposable = responseRepository
                .observeOn(mainThreadScheduler)
                .subscribe(urlList -> {
                    urlChannelsListLocal = urlList;
                    getChannelsList(urlChannelsListLocal);
                }, throwable -> {
                    channelsListView.hideLoading();
                    channelsListView.showError(Command.REFRESH_URL);
                });
        App.getInstance().getCompositeDisposable().add(disposable);
    }

    private void getChannelsList(List<String> urlList) {
        Single<List<Feed>> responseRepository = channelsListUseCase.getChannelsList(Command.REFRESH_CHANNELS, urlList);

        disposable = responseRepository
                .observeOn(mainThreadScheduler)
                .subscribe(channelList -> {
                    channelsListLocal = channelList;
                    updateUrlsChannelList(channelsListLocal);
                    channelsListView.hideLoading();
                    channelsListView.refreshChannelsListRVAdapter();
                }, throwable -> {
                    channelsListView.hideLoading();
                    channelsListView.showError(Command.REFRESH_CHANNELS);
                });

        App.getInstance().getCompositeDisposable().add(disposable);
    }

    public void putUrlsChannelList() {
        urlsChannelListUseCase.putUrlChannelsList(urlChannelsListLocal);
    }

    private void updateUrlsChannelList(List<Feed> urlList) {
        //TODo: вынести в юзкейс
        urlChannelsListLocal.clear();
        for (int i = 0; i < urlList.size(); i++) {
            urlChannelsListLocal.add(urlList.get(i).getUrl());
        }
        Timber.d("rty количество url "+ urlChannelsListLocal.size());
    }
}

