package com.olskrain.aggregatornews.data.repository;

import android.content.Intent;

import com.olskrain.aggregatornews.Common.App;
import com.olskrain.aggregatornews.Common.Command;
import com.olskrain.aggregatornews.Common.NetworkStatus;
import com.olskrain.aggregatornews.data.cache.ChannelsListCache;
import com.olskrain.aggregatornews.data.cache.IChannelsListCache;
import com.olskrain.aggregatornews.data.repository.service.DataDownloadService;

import java.util.List;

/**
 * Created by Andrey Ievlev on 27,Апрель,2019
 */

public class AllChannelsListRepository implements IAllChannelsListRepository, ChannelsListCache.IResponseDBCallback {

    public interface IResponseDBCallback {
        void sendMessageStatusCallingBack(String message);

        void sendChannelsListCallingBack(List<String> channelsList);
    }

    private IChannelsListCache cache;
    private IResponseDBCallback callback;

    public AllChannelsListRepository() {
        cache = new ChannelsListCache();
        ((ChannelsListCache) cache).registerCallBack(this);
    }

    public void registerCallBack(IResponseDBCallback callback) {
        this.callback = callback;
    }

    @Override
    public void putUpdatedData(Command command, String urlChannel) {
        if (command.equals(Command.ADD_CHANNEL)) {
            getChannel(urlChannel);
        }
        //тут потом мы должны положить объект канала
        cache.updateDatabase(command, urlChannel);
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

    @Override
    public void sendMessageStatusCallingBack(String message) {
        callback.sendMessageStatusCallingBack(message);
    }

    @Override
    public void sendChannelsListCallingBack(List<String> channelsList) {
        callback.sendChannelsListCallingBack(channelsList);
    }

    private void startService(String urlChannel) {
        Intent intentDataDownloadService = new Intent(App.getInstance(), DataDownloadService.class);
        App.getInstance().startService(intentDataDownloadService.putExtra("Url", urlChannel));
    }
}
