package com.olskrain.aggregatornews.presentation.presenter;

import android.annotation.SuppressLint;

import com.olskrain.aggregatornews.Common.Command;
import com.olskrain.aggregatornews.abctractFactory.FactoryProvider;
import com.olskrain.aggregatornews.domain.entities.Feed;
import com.olskrain.aggregatornews.domain.usecase.interfaceUseCase.IChannelsListUseCase;
import com.olskrain.aggregatornews.domain.usecase.interfaceUseCase.IUrlsChannelListUseCase;
import com.olskrain.aggregatornews.presentation.presenter.interfacePresenter.IChannelsListPresenter;
import com.olskrain.aggregatornews.presentation.presenter.interfaceRecycleListPresenter.IChannelListRVPresenter;
import com.olskrain.aggregatornews.presentation.ui.view.IChannelsListView;
import com.olskrain.aggregatornews.presentation.ui.view.item.IChannelListItemView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by Andrey Ievlev on 03,Май,2019
 */

public class ChannelsListPresenter implements IChannelsListPresenter {
    public class ChannelRecycleListRVPresenter implements IChannelListRVPresenter {
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
        public void bindView(final IChannelListItemView rowView) {
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

    public ChannelRecycleListRVPresenter channelRecycleListPresenter;
    private final IChannelsListView channelsListView;
    private final IChannelsListUseCase channelsListUseCase = FactoryProvider.providerUseCaseFactory().createChannelsListUseCase();
    private final IUrlsChannelListUseCase urlsChannelListUseCase = FactoryProvider.providerUseCaseFactory().createUrlsChannelListUseCase();
    private final Scheduler mainThreadScheduler;
    private final CompositeDisposable compositeDisposable;
    private List<Feed> channelsListLocal = new ArrayList<>();
    private List<String> urlChannelsListLocal = new ArrayList<>();
    private Disposable disposable;
    private String currentUrlChannel;

    public ChannelsListPresenter(final IChannelsListView view, final CompositeDisposable compositeDisposable, final Scheduler mainThreadScheduler) {
        this.channelsListView = view;
        this.compositeDisposable = compositeDisposable;
        this.mainThreadScheduler = mainThreadScheduler;
        this.channelRecycleListPresenter = new ChannelRecycleListRVPresenter();
    }

    @SuppressLint("CheckResult")
    @Override
    public void attachView() {
        channelRecycleListPresenter.clickItem.subscribe(iChannelListItemView ->
                channelsListView.goToChannelDetailFragment(channelsListLocal.get(iChannelListItemView.getCurrentPosition()).getUrl()));

        channelRecycleListPresenter.clickMenu.subscribe(iChannelListItemView -> {
            currentUrlChannel = channelsListLocal.get(iChannelListItemView.getCurrentPosition()).getUrl();
            channelsListView.showBottomSheet(channelsListLocal.get(iChannelListItemView.getCurrentPosition()));
        });
    }

    @Override
    public void checkDuplicate(final String urlChannel) {
        channelsListView.showLoading();
        Completable responseRepository = channelsListUseCase.checkDuplicate(urlChannel, urlChannelsListLocal);

        disposable = responseRepository
                .observeOn(mainThreadScheduler)
                .subscribe(() -> {
                    addNewChannel(urlChannel);
                }, throwable -> {
                    channelsListView.hideLoading();
                    channelsListView.showError(Command.ERROR_CHECK_DUPLICATE);
                });

        compositeDisposable.add(disposable);
    }

    private void addNewChannel(final String urlChannel) {
        channelsListView.showLoading();
        Single<Feed> responseRepository = channelsListUseCase.addNewChannel(Command.ADD_CHANNEL, urlChannel);

        disposable = responseRepository
                .observeOn(mainThreadScheduler)
                .subscribe(channel -> {
                    channelsListLocal.add(channel);
                    urlChannelsListLocal = urlsChannelListUseCase.addUrlChannel(urlChannelsListLocal, urlChannel);
                    channelsListView.hideLoading();
                    channelsListView.refreshChannelsListRVAdapter();
                }, throwable -> {
                    channelsListView.hideLoading();
                    channelsListView.showError(Command.ADD_CHANNEL);
                });

        compositeDisposable.add(disposable);
    }

    @Override
    public void showDeletionWarning(Command command) {
        Completable responseUser = channelsListView.showWarning(command);
        disposable = responseUser.subscribe(() -> {
            deleteChannel();
        }, throwable -> {
            //channelsListView.showErrorEditText(Command.ERROR_DIFFERENT);
        });

        compositeDisposable.add(disposable);
    }

    private void deleteChannel() {
        channelsListView.showLoading();
        Completable responseRepository = channelsListUseCase.deleteChannel(currentUrlChannel);
        disposable = responseRepository
                .observeOn(mainThreadScheduler)
                .subscribe(() -> {
                    getChannelListDB();
                    urlChannelsListLocal = urlsChannelListUseCase.deleteUrlChannel(urlChannelsListLocal, currentUrlChannel);
                    channelsListView.hideLoading();
                    channelsListView.refreshChannelsListRVAdapter();
                }, throwable -> {
                    channelsListView.hideLoading();
                    channelsListView.showError(Command.ERROR_DIFFERENT);
                });
        compositeDisposable.add(disposable);
    }

    @Override
    public void deleteAllChannels() {
        channelsListView.showLoading();
        Completable responseRepository = channelsListUseCase.deleteAllChannels();
        disposable = responseRepository
                .observeOn(mainThreadScheduler)
                .subscribe(() -> {
                    getChannelListDB();
                    urlChannelsListLocal = urlsChannelListUseCase.deleteAllUrlsChannel(urlChannelsListLocal);
                }, throwable -> {
                    channelsListView.hideLoading();
                    channelsListView.showError(Command.ERROR_DIFFERENT);
                });
        compositeDisposable.add(disposable);
    }

    private void getChannelListDB() {
        Single<List<Feed>> responseRepository = channelsListUseCase.getChannelListDB();
        disposable = responseRepository
                .observeOn(mainThreadScheduler)
                .subscribe(channelList -> {
                    channelsListLocal = channelList;
                    channelsListView.hideLoading();
                    channelsListView.refreshChannelsListRVAdapter();
                }, throwable -> {
                    channelsListView.hideLoading();
                    channelsListView.showError(Command.REFRESH_CHANNELS);
                });

        compositeDisposable.add(disposable);
    }

    @Override
    public void getUrlsChannelList() {
        channelsListView.showLoading();
        Single<List<String>> responseRepository = urlsChannelListUseCase.getUrlsChannelList();

        disposable = responseRepository
                .observeOn(mainThreadScheduler)
                .subscribe(urlList -> {
                    urlChannelsListLocal = urlList;
                    refreshChannelsList();
                }, throwable -> {
                    channelsListView.hideLoading();
                    channelsListView.showError(Command.REFRESH_URL);
                });
        compositeDisposable.add(disposable);
    }

    @Override
    public void refreshChannelsList() {
        Single<List<Feed>> responseRepository = channelsListUseCase.refreshChannelsList(Command.REFRESH_CHANNELS, urlChannelsListLocal);

        disposable = responseRepository
                .observeOn(mainThreadScheduler)
                .subscribe(channelList -> {
                    channelsListLocal = channelList;
                    channelsListView.hideLoading();
                    channelsListView.refreshChannelsListRVAdapter();
                }, throwable -> {
                    channelsListView.hideLoading();
                    channelsListView.showError(Command.REFRESH_CHANNELS);
                });

        compositeDisposable.add(disposable);
    }

    @Override
    public void saveUrlsChannelList() {
        urlsChannelListUseCase.saveUrlChannelsList(urlChannelsListLocal);
    }

    @Override
    public void goToAddChannelActivity() {
        channelsListView.goToAddChannelActivity();
    }
}

