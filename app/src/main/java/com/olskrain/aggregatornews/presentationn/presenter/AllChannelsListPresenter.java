package com.olskrain.aggregatornews.presentationn.presenter;

import com.olskrain.aggregatornews.domain.interactor.MainInteractor;
import com.olskrain.aggregatornews.presentationn.presenter.list.IChannelListPresenter;
import com.olskrain.aggregatornews.presentationn.ui.view.IAllChannelsListView;
import com.olskrain.aggregatornews.presentationn.ui.view.item.IChannelListItemView;

import java.util.List;

/**
 * Created by Andrey Ievlev on 03,Май,2019
 */

public class AllChannelsListPresenter implements MainInteractor.IResponseDBCallback {
    public class ChannelListPresenter implements IChannelListPresenter { //презентер для списка
        @Override
        public void bindView(IChannelListItemView rowView) {
            rowView.setTitle(channelsListLocal.get(rowView.getPos()));
        }

        @Override
        public int getChannelCount() {
            return channelsListLocal == null ? 0 : channelsListLocal.size();
        }
    }

    public ChannelListPresenter ChannelListPresenter;
    private IAllChannelsListView allChannelsListView;
    private MainInteractor mainInteractor;
    private List<String> channelsListLocal;

    public AllChannelsListPresenter(IAllChannelsListView view) {
        this.allChannelsListView = view;
        this.mainInteractor = new MainInteractor();
        mainInteractor.registerCallBack(this);
        this.ChannelListPresenter = new ChannelListPresenter();
    }

    public void addNewChannel(String urlChannel) {
        mainInteractor.addNewChannel(urlChannel);
        allChannelsListView.refreshChannelsListRVAdapter();
    }

    public void deleteChannel(String urlChannel) {
        mainInteractor.deleteChannel(urlChannel);
        allChannelsListView.refreshChannelsListRVAdapter();
    }

    public void deleteAllChannels() {
        mainInteractor.deleteAllChannels();
        allChannelsListView.refreshChannelsListRVAdapter();
    }

    public void refreshChannelsList() {
        allChannelsListView.showLoading();
        mainInteractor.refreshChannelsList();
    }

    @Override
    public void sendMessageStatusCallingBack(String message) {
        allChannelsListView.displayMessages(message);
    }

    @Override
    public void sendChannelsListCallingBack(List<String> channelsList) {
        channelsListLocal = channelsList;
        allChannelsListView.refreshChannelsListRVAdapter();
        allChannelsListView.hideLoading();
    }
}

