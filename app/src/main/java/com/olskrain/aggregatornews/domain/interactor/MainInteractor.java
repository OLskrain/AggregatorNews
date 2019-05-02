package com.olskrain.aggregatornews.domain.interactor;

import com.olskrain.aggregatornews.data.repository.ChannelsListRepository;
import com.olskrain.aggregatornews.data.repository.IChannelsListRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey Ievlev on 01,Май,2019
 */

public class MainInteractor {

    public interface ICallback {
        void sendMessageStatusCallingBack(String message);
        void sendChannelsListCallingBack(List<String> channelsList);
    }

    private IChannelsListRepository channelRepository;
    private ICallback callback;
    private List<String> channelsList;

    public MainInteractor() {
        channelsList = new ArrayList<>();
        channelRepository = new ChannelsListRepository();
    }

    public void registerCallBack(ICallback callback) {
        this.callback = callback;
    }

    public String addNewChannel(String url){
        channelsList.add(url);
        callback.sendMessageStatusCallingBack("Канал добавлен");
        callback.sendChannelsListCallingBack(channelsList);
        return channelRepository.putChannelsList(channelsList);
    }

    public String deleteChannel(String url){
        channelsList.remove(url);
        callback.sendMessageStatusCallingBack("Канал Удален");
        callback.sendChannelsListCallingBack(channelsList);
        return channelRepository.putChannelsList(channelsList);
    }

    public String deleteAllChannels(){
        channelsList.clear();
        callback.sendMessageStatusCallingBack("Список каналов очищен");
        callback.sendChannelsListCallingBack(channelsList);
        return channelRepository.putChannelsList(channelsList);
    }

    public List<String> refreshChannelsList() {
            return channelsList = channelRepository.getChannelsList();
    }
}
