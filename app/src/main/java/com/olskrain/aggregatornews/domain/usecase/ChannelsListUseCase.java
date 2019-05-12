package com.olskrain.aggregatornews.domain.usecase;

import com.olskrain.aggregatornews.data.repository.ChannelsListRepository;
import com.olskrain.aggregatornews.data.repository.IAllChannelsListRepository;
import com.olskrain.aggregatornews.domain.entities.Channel;

import java.util.List;

/**
 * Created by Andrey Ievlev on 01,Май,2019
 */

public class ChannelsListUseCase implements IChannelsListUseCase, ChannelsListRepository.IResponseDBCallback {

    public interface IResponseDBCallback {
        void onMessageStatus(String message);
        void onChannelsList(List<Channel> channelsList);
    }

    private IAllChannelsListRepository channelRepository;
    private IResponseDBCallback callback;
    private List<Channel> channelsList;

    public ChannelsListUseCase(List<Channel> channelsList) {
        this.channelsList = channelsList;
        this.channelRepository = new ChannelsListRepository();
        ((ChannelsListRepository) channelRepository).registerCallBack(this);
    }

    public void registerCallBack(IResponseDBCallback callback) {
        this.callback = callback;
    }

    @Override
    public void addNewChannel(String urlChannel) {
        channelRepository.getChannel(urlChannel);
    }

    @Override
    public void deleteChannel(int position) {
        channelsList.remove(position);
        onChannelsList(channelsList);
    }

    @Override
    public void deleteAllChannels() {
        channelsList.clear();
        onChannelsList(channelsList);
    }

    @Override
    public void refreshChannelsList() {
        channelRepository.getChannelsList();
        onChannelsList(channelsList);
    }

    @Override
    public void putChannelsList() {
        channelRepository.putUpdatedData(channelsList);
    }

    @Override
    public void onMessageStatus(String message) {
        callback.onMessageStatus(message);
    }

    @Override
    public void onChannelsList(List<Channel> channelsList) {
        this.channelsList = channelsList;
        callback.onChannelsList(channelsList);
    }
}
