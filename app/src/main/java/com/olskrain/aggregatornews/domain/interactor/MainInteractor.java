package com.olskrain.aggregatornews.domain.interactor;

import com.olskrain.aggregatornews.Common.Command;
import com.olskrain.aggregatornews.data.repository.AllChannelsListRepository;
import com.olskrain.aggregatornews.data.repository.IAllChannelsListRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey Ievlev on 01,Май,2019
 */

public class MainInteractor implements AllChannelsListRepository.IResponseDBCallback {

    public interface IResponseDBCallback {
        void sendMessageStatusCallingBack(String message);
        void sendChannelsListCallingBack(List<String> channelsList);
    }

    private IAllChannelsListRepository channelRepository;
    private IResponseDBCallback callback;
    private List<String> channelsList;

    public MainInteractor() {
        channelsList = new ArrayList<>();
        channelRepository = new AllChannelsListRepository();
        ((AllChannelsListRepository) channelRepository).registerCallBack(this);
    }

    public void registerCallBack(IResponseDBCallback callback) {
        this.callback = callback;
    }

    public void addNewChannel(String urlChannel) {
        channelsList.add(urlChannel);
        callback.sendMessageStatusCallingBack("Канал добавлен");
        channelRepository.putUpdatedData(Command.ADD_CHANNEL, urlChannel);
    }

    public void deleteChannel(String urlChannel) {
        channelsList.remove(urlChannel);
        callback.sendMessageStatusCallingBack("Канал Удален");
        channelRepository.putUpdatedData(Command.DELETE_CHANNEL, urlChannel);
    }

    public void deleteAllChannels() {
        channelsList.clear();
        callback.sendMessageStatusCallingBack("Список каналов очищен");
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
    public void sendChannelsListCallingBack(List<String> channelsList) {
        //this.channelsList = channelsList;
        callback.sendChannelsListCallingBack(channelsList);
    }
}
