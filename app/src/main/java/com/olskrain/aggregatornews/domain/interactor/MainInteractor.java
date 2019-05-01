package com.olskrain.aggregatornews.domain.interactor;

import com.olskrain.aggregatornews.Common.Command;
import com.olskrain.aggregatornews.data.repository.ChannelRepository;
import com.olskrain.aggregatornews.data.repository.IChannelRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey Ievlev on 01,Май,2019
 */

public class MainInteractor {

    public interface ICallback {
        void callingBackSendMessage(String message);
    }

    private IChannelRepository channelRepository;
    private List<String> channelsList;
    private ICallback callback;

    public MainInteractor() {
        channelsList = new ArrayList<>();
        channelRepository = new ChannelRepository();
    }

    public void registerCallBack(ICallback callback) {
        this.callback = callback;
    }

    public String actionsChannelsList(Command command, String url){
        switch (command){
            case ADD_CHANNEL:
                channelsList.add(url);
                callback.callingBackSendMessage("Канал добавлен");
                break;
            case DELETE_CHANNEL:
                channelsList.remove(url);
                callback.callingBackSendMessage("Канал Удален");
                break;
            case DELETE_ALL_CHANNELS:
                channelsList.clear();
                callback.callingBackSendMessage("Список каналов очищен");
                break;
        }
        return channelRepository.putChannelsList(channelsList);
    }
}
