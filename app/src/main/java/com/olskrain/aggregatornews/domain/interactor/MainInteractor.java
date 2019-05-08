package com.olskrain.aggregatornews.domain.interactor;

import com.olskrain.aggregatornews.Common.Command;
import com.olskrain.aggregatornews.data.repository.AllChannelsListRepository;
import com.olskrain.aggregatornews.data.repository.IAllChannelsListRepository;
import com.olskrain.aggregatornews.domain.entities.Channel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey Ievlev on 01,Май,2019
 */

public class MainInteractor implements AllChannelsListRepository.IResponseDBCallback {

    public interface IResponseDBCallback {
        void sendMessageStatusCallingBack(String message);
        void sendChannelsListCallingBack(List<Channel> channelsList);
    }

    private IAllChannelsListRepository channelRepository;
    private IResponseDBCallback callback;
    private List<Channel> channelsList;

    public MainInteractor() {
        channelsList = new ArrayList<>();
        channelRepository = new AllChannelsListRepository();
        ((AllChannelsListRepository) channelRepository).registerCallBack(this);
    }

    public void registerCallBack(IResponseDBCallback callback) {
        this.callback = callback;
    }

    public void addNewChannel(String urlChannel) {
        channelRepository.getChannel(urlChannel);
    }

    public void deleteChannel(String urlChannel) {
        channelRepository.putUpdatedData(Command.DELETE_CHANNEL, channelsList);
    }

    public void deleteAllChannels() {
        channelRepository.putUpdatedData(Command.DELETE_ALL_CHANNELS, null);
    }

    public void refreshChannelsList() {
        channelRepository.getChannelsList();
    }

    @Override
    public void sendMessageStatusCallingBack(String message) {
        callback.sendMessageStatusCallingBack(message);
    }

    @Override
    public void sendChannelsListCallingBack(List<Channel> channelsList) {
        this.channelsList = channelsList;
        callback.sendChannelsListCallingBack(channelsList);
    }
}
