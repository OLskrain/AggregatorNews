package com.olskrain.aggregatornews.presentationn.presenter;

import com.olskrain.aggregatornews.domain.entities.Channel;
import com.olskrain.aggregatornews.domain.usecase.ChannelsListUseCase;
import com.olskrain.aggregatornews.presentationn.presenter.list.IChannelListPresenter;
import com.olskrain.aggregatornews.presentationn.ui.view.IAllChannelsListView;
import com.olskrain.aggregatornews.presentationn.ui.view.item.IChannelListItemView;

import java.util.List;

/**
 * Created by Andrey Ievlev on 03,Май,2019
 */

public class AllChannelsListPresenter implements ChannelsListUseCase.IResponseDBCallback {
    public class ChannelListPresenter implements IChannelListPresenter {
        @Override
        public void bindView(IChannelListItemView rowView) {
            String channelTitle = channelsListLocal.get(rowView.getPos()).getFeed().getTitle();
            String lastBuildDate = channelsListLocal.get(rowView.getPos()).getFeed().getLastBuildDate();
            rowView.setTitle(channelTitle);
            rowView.setLastBuildDate(lastBuildDate);
        }

        @Override
        public int getChannelCount() {
            return channelsListLocal == null ? 0 : channelsListLocal.size();
        }
    }

    public ChannelListPresenter channelListPresenter;
    private IAllChannelsListView allChannelsListView;
    private ChannelsListUseCase channelsListUseCase;
    private List<Channel> channelsListLocal;

    public AllChannelsListPresenter(IAllChannelsListView view) {
        this.allChannelsListView = view;
        this.channelsListUseCase = new ChannelsListUseCase(channelsListLocal);
        this.channelsListUseCase.registerCallBack(this);
        this.channelListPresenter = new ChannelListPresenter();
    }

    public void addNewChannel(String urlChannel) {
        allChannelsListView.showLoading();
        channelsListUseCase.addNewChannel(urlChannel);
        allChannelsListView.refreshChannelsListRVAdapter();
    }

    public void deleteChannel(int channelPosition) {
        allChannelsListView.showLoading();
        channelsListUseCase.deleteChannel(channelPosition);
        allChannelsListView.refreshChannelsListRVAdapter();
    }

    public void deleteAllChannels() {
        allChannelsListView.showLoading();
        channelsListUseCase.deleteAllChannels();
        allChannelsListView.refreshChannelsListRVAdapter();
    }

    public void refreshChannelsList() {
        allChannelsListView.showLoading();
        channelsListUseCase.refreshChannelsList();
    }

    public void putChannelsList() {
        channelsListUseCase.putChannelsList();
    }

    @Override
    public void sendMessageStatusCallingBack(String message) {
        allChannelsListView.displayMessages(message);
        allChannelsListView.hideLoading();
    }

    @Override
    public void sendChannelsListCallingBack(List<Channel> channelsList) {
        channelsListLocal = channelsList;
        allChannelsListView.refreshChannelsListRVAdapter();
        allChannelsListView.hideLoading();
    }
}

