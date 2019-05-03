package com.olskrain.aggregatornews.presentationn.presenter;

import com.olskrain.aggregatornews.domain.interactor.MainInteractor;
import com.olskrain.aggregatornews.presentationn.presenter.list.IChannelListPresenter;
import com.olskrain.aggregatornews.presentationn.ui.view.IMainView;
import com.olskrain.aggregatornews.presentationn.ui.view.item.IChannelListItemView;

import java.util.List;

/**
 * Created by Andrey Ievlev on 22,Апрель,2019
 */

public class MainPresenter implements MainInteractor.IResponseDBCallback {
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

    public ChannelListPresenter channelListPresenter;
    private IMainView mainView;
    private MainInteractor mainInteractor;
    private List<String> channelsListLocal;

    public MainPresenter(IMainView view) {
        this.mainView = view;
        this.mainInteractor = new MainInteractor();
        mainInteractor.registerCallBack(this);
        this.channelListPresenter = new ChannelListPresenter();
    }

    public void addNewChannel(String urlChannel) {
        mainInteractor.addNewChannel(urlChannel);
        mainView.refreshChannelsListRVAdapter();
    }

    public void deleteChannel(String urlChannel) {
        mainInteractor.deleteChannel(urlChannel);
        mainView.refreshChannelsListRVAdapter();
    }

    public void deleteAllChannels() {
        mainInteractor.deleteAllChannels();
        mainView.refreshChannelsListRVAdapter();
    }

    public void refreshChannelsList() {
        mainView.showLoading();
        mainInteractor.refreshChannelsList();
    }

    @Override
    public void sendMessageStatusCallingBack(String message) {
        mainView.displayMessages(message);
    }

    @Override
    public void sendChannelsListCallingBack(List<String> channelsList) {
        channelsListLocal = channelsList;
        mainView.refreshChannelsListRVAdapter();
        mainView.hideLoading();
    }
}
