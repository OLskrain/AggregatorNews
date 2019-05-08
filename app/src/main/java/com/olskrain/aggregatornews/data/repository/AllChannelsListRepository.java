package com.olskrain.aggregatornews.data.repository;

import android.content.Intent;

import com.olskrain.aggregatornews.Common.App;
import com.olskrain.aggregatornews.Common.Command;
import com.olskrain.aggregatornews.Common.NetworkStatus;
import com.olskrain.aggregatornews.data.cache.ChannelsListCache;
import com.olskrain.aggregatornews.data.cache.IChannelsListCache;
import com.olskrain.aggregatornews.data.repository.service.DataDownloadService;
import com.olskrain.aggregatornews.domain.entities.Channel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey Ievlev on 27,Апрель,2019
 */

public class AllChannelsListRepository implements IAllChannelsListRepository,
        ChannelsListCache.IResponseDBCallback, ResponseServiceBroadcast.IResponseServerCallback {

    public interface IResponseDBCallback {
        void sendMessageStatusCallingBack(String message);

        void sendChannelsListCallingBack(List<Channel> channelsList);
    }

    private IChannelsListCache cache;
    private IResponseDBCallback callback;
    private ResponseServiceBroadcast responseServiceBroadcast;

    List<Channel> channelsList = new ArrayList<>(); //временно для подмены

    public AllChannelsListRepository() {
        cache = new ChannelsListCache();
        //responseServiceBroadcast = new ResponseServiceBroadcast();
        ((ChannelsListCache) cache).registerCallBack(this);
        App.getResponseServiceBroadcast().registerCallBack(this);
        //responseServiceBroadcast.registerCallBack(this);
    }

    public void registerCallBack(IResponseDBCallback callback) {
        this.callback = callback;
    }

    @Override
    public void putUpdatedData(Command command, List<Channel> channelsList) {
         cache.updateDatabase(command, channelsList);
//        /**
//         * Временная подмена данных
//         */
//        if (command.equals(Command.ADD_CHANNEL)) {
//            channelsList.add(channel);
//            sendChannelsListCallingBack(channelsList);
//        } else if (command.equals(Command.DELETE_CHANNEL)) {
//            channelsList.remove(0);
//            sendChannelsListCallingBack(channelsList);
//        } else if (command.equals(Command.DELETE_ALL_CHANNELS)) {
//            channelsList.clear();
//            sendChannelsListCallingBack(channelsList);
//        }
    }

    @Override
    public void getChannelsList() {
        cache.getData();
    }

    @Override
    public void getChannel(String urlChannel) {
        if (NetworkStatus.isOnline()) {
            startService(urlChannel);
        } else {
            //Todo:если нет инета
        }
    }

    private void startService(String urlChannel) {
        Intent intentDataDownloadService = new Intent(App.getInstance(), DataDownloadService.class);
        App.getInstance().startService(intentDataDownloadService.putExtra("Url", urlChannel));
    }

    @Override
    public void sendMessageStatusCallingBack(String message) {
        callback.sendMessageStatusCallingBack(message);
    }

    @Override
    public void sendChannelsListCallingBack(List<Channel> channelsList) {
        callback.sendChannelsListCallingBack(channelsList);
    }

    @Override
    public void sendChannelCallingBack(Channel channel) {
        List<Channel> channelsList = new ArrayList<>();
        channelsList.add(channel);
        putUpdatedData(Command.ADD_CHANNEL, channelsList);
    }
}
