package com.olskrain.aggregatornews.presentation.presenter;

import android.annotation.SuppressLint;

import com.olskrain.aggregatornews.domain.entities.Feed;
import com.olskrain.aggregatornews.domain.usecase.ChannelsListUseCase;
import com.olskrain.aggregatornews.domain.usecase.IChannelsListUseCase;
import com.olskrain.aggregatornews.presentation.presenter.list.IChannelListPresenter;
import com.olskrain.aggregatornews.presentation.ui.view.IChannelsListView;
import com.olskrain.aggregatornews.presentation.ui.view.item.IChannelListItemView;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.subjects.PublishSubject;
import timber.log.Timber;

/**
 * Created by Andrey Ievlev on 03,Май,2019
 */

public class ChannelsListPresenter {
    public class ChannelListPresenter implements IChannelListPresenter {
        private IChannelListItemView rowView;
        private final PublishSubject<IChannelListItemView> clickItem = PublishSubject.create();
        private final PublishSubject<IChannelListItemView> clickMenu = PublishSubject.create();

        public IChannelListItemView getRowView() {
            return rowView;
        }

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
            this.rowView = rowView;
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
    private Scheduler mainThreadScheduler;
    private List<Feed> channelsListLocal;

    public ChannelsListPresenter(IChannelsListView view, Scheduler mainThreadScheduler) {
        this.channelsListView = view;
        this.mainThreadScheduler = mainThreadScheduler;
        this.channelsListUseCase = new ChannelsListUseCase();
        this.channelListPresenter = new ChannelListPresenter();
    }

    @SuppressLint("CheckResult")
    public void attachView(){
        channelListPresenter.clickItem.subscribe(iChannelListItemView ->
                channelsListView.goToChannelDetailFragment(channelListPresenter.getRowView().getPos()));

        channelListPresenter.clickMenu.subscribe(iChannelListItemView ->
                channelsListView.showBottomSheet(channelListPresenter.getRowView().getPos()));
    }

    public void addNewChannel(String urlChannel) {
        channelsListView.showLoading();
        channelsListUseCase.addNewChannel(urlChannel)
                .observeOn(mainThreadScheduler)
                .subscribe(channelList -> {
                    channelsListLocal = channelList;
                    Timber.d("rty size " + channelList.size() );
                    channelsListView.hideLoading();
                    channelsListView.refreshChannelsListRVAdapter();
                }, throwable -> {

                });

    }

    public void deleteAllChannels() {
        // channelsListUseCase.deleteAllChannels(channelsListLocal);
        channelsListView.refreshChannelsListRVAdapter();
    }

    public void refreshChannelsList() {
        channelsListView.showLoading();
        //channelsListUseCase.refreshChannelsList();
    }

    public void putChannelsList() {
        //channelsListUseCase.putChannelsList(channelsListLocal);
    }

//    @Override
//    public void onMessageStatus(String message) {
//        channelsListView.displayMessages(message);
//        channelsListView.hideLoading();
//    }
//
//    @Override
//    public void onChannelsList(List<Channel> channelsList) {
//        channelsListLocal = channelsList;
//        channelsListView.refreshChannelsListRVAdapter();
//        channelsListView.hideLoading();
//    }
}

