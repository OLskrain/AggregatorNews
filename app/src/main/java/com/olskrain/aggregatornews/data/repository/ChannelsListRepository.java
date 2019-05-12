package com.olskrain.aggregatornews.data.repository;

import android.content.Intent;
import android.content.SharedPreferences;

import com.olskrain.aggregatornews.Common.App;
import com.olskrain.aggregatornews.Common.NetworkStatus;
import com.olskrain.aggregatornews.data.cache.ChannelsListCache;
import com.olskrain.aggregatornews.data.cache.IChannelsListCache;
import com.olskrain.aggregatornews.data.repository.service.DataDownloadService;
import com.olskrain.aggregatornews.domain.entities.Channel;

import java.util.List;

/**
 * Created by Andrey Ievlev on 27,Апрель,2019
 */

public class ChannelsListRepository implements IAllChannelsListRepository,
        ChannelsListCache.IResponseDBCallback, ResponseServiceBroadcast.IResponseServerCallback {

    public interface IResponseDBCallback {
        void onMessageStatus(String message);
        void onChannelsList(List<Channel> channelsList);
    }

    private static final String NO_CONNECTION = "Нет подключения к интернету!";
    private static final String EXTRA_KEY = "extra key";
    private static final String BUNCH_KEYS = "bunch keys";
    private static final String DEFAULT_VALUE_ = "value";


    private IChannelsListCache cache;
    private IResponseDBCallback callback;
    private List<Channel> channelsList;

    public ChannelsListRepository() {
        this.cache = new ChannelsListCache();
        ((ChannelsListCache) cache).registerCallBack(this);
        App.getResponseServiceBroadcast().registerCallBack(this);
    }

    public void registerCallBack(IResponseDBCallback callback) {
        this.callback = callback;
    }

    @Override
    public void putUpdatedData(List<Channel> channelsList) {
        cache.updateDatabase(channelsList);
        putUrlChannelsList(channelsList);
    }

    private void putUrlChannelsList(List<Channel> channelsList) {
        SharedPreferences.Editor editor = App.getSharedPreferences().edit();
        editor.putInt(BUNCH_KEYS, channelsList.size());
        editor.apply();

        for (int i = 0; i < channelsList.size(); i++) {
            editor.putString(Integer.toString(i), channelsList.get(i).getFeed().getUrl());
            editor.apply();
        }
    }

    private String[] getUrlChannelList() {
        int bunchKeys = App.getSharedPreferences().getInt(BUNCH_KEYS, 25);
        String[] urlsChannel = new String[bunchKeys];

        for (int i = 0; i < bunchKeys; i++) {
            String currentUrl = App.getSharedPreferences().getString(Integer.toString(i), DEFAULT_VALUE_);
            urlsChannel[i] = currentUrl;
        }
        return urlsChannel;
    }

    @Override
    public void getChannelsList() {
        if (NetworkStatus.isOnline()) {
            //TODo:сделать запрос на сервер, и если приходит ошибка то вызываем ке
            //String[] st = getUrlChannelList();
            //startService(st);
            cache.getData();
        } else {
            cache.getData();
        }

    }

    @Override
    public void getChannel(String urlChannel) {
        if (NetworkStatus.isOnline()) {
            String[] currentUrlList = new String[1];
            currentUrlList[0] = urlChannel;
            startService(currentUrlList);
        } else {
            onMessageStatus(NO_CONNECTION);
        }
    }

    private void startService(String[] urlChannel) {
        Intent intentDataDownloadService = new Intent(App.getInstance(), DataDownloadService.class);
        App.getInstance().startService(intentDataDownloadService.putExtra(EXTRA_KEY, urlChannel));
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

    @Override
    public void sendChannelCallingBack(Channel channel) {
        channelsList.add(channel);
        onChannelsList(channelsList);
        putUpdatedData(channelsList);
    }
}
