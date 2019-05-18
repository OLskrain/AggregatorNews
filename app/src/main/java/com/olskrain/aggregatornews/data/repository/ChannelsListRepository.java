package com.olskrain.aggregatornews.data.repository;

import android.content.SharedPreferences;

import com.olskrain.aggregatornews.Common.App;
import com.olskrain.aggregatornews.Common.NetworkStatus;
import com.olskrain.aggregatornews.Common.XmlRssParser;
import com.olskrain.aggregatornews.data.api.HTTPDataHandler;
import com.olskrain.aggregatornews.data.cache.ChannelsListCache;
import com.olskrain.aggregatornews.data.cache.IChannelsListCache;
import com.olskrain.aggregatornews.domain.entities.Channel;
import com.olskrain.aggregatornews.domain.entities.Feed;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Andrey Ievlev on 27,Апрель,2019
 */

public class ChannelsListRepository implements IChannelsListRepository {


    private static final String NO_CONNECTION = "Нет подключения к интернету!";
    private static final String NO_CHANNEL = "no channel";
    private static final String EXTRA_KEY = "extra key";
    private static final String BUNCH_KEYS = "bunch keys";
    private static final String DEFAULT_VALUE_ = "value";


    private IChannelsListCache cache;
    private HTTPDataHandler httpDataHandler;
    private XmlRssParser xmlRssParser;
    private List<Feed> channelsList;
    private int counter;

    public ChannelsListRepository() {
        this.cache = new ChannelsListCache();
        this.httpDataHandler = new HTTPDataHandler();
        this.xmlRssParser = new XmlRssParser();
        this.channelsList = new ArrayList<>();
        //  App.getResponseServiceBroadcast().registerCallBack(this);
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

//    @Override
//    public void getChannelsList() {
//        if (NetworkStatus.isOnline()) {
//            //TODo:сделать запрос на сервер, и если приходит ошибка то вызываем ке
//            //String[] st = getUrlChannelList();
//            //startService(st);
//            cache.getData();
//        } else {
//            cache.getData();
//        }
//
//    }

    @Override
    public Single<List<Feed>> getChannelsList(List<String> urlList) {
        if (NetworkStatus.isOnline()) {
            return Single.create(emitter -> {
                HTTPDataHandler httpDataHandler = new HTTPDataHandler();
                XmlRssParser xmlRssParser = new XmlRssParser();

                for (int i = 0; i < urlList.size(); i++) {
                    String responseServer = httpDataHandler.getHTTPData(urlList.get(i));
                    Channel channel = xmlRssParser.parseData(urlList.get(i), responseServer);
                    channelsList.add(channel.getFeed());
                }

                if (channelsList.isEmpty()) {
                    emitter.onError(new RuntimeException("List пуст"));
                } else {
                   // cache.updateDatabase(channelsList);
                    emitter.onSuccess(channelsList);
                }
            }).subscribeOn(Schedulers.io()).cast((Class<List<Feed>>) (Class) List.class);
        } else {
            //Todo: если нет интернета
            return null;
        }
    }

    public void onMessageStatus(String message) {

    }

//    @Override
//    public void onMessageStatus(String message) {
//        callback.onMessageStatus(message);
//    }
//
//    @Override
//    public void onChannelsList(List<Channel> channelsList) {
//        this.channelsList = channelsList;
//        callback.onChannelsList(channelsList);
//    }
//
//    @Override
//    public void sendChannelCallingBack(Channel channel) {
//        channelsList.add(channel);
//        onChannelsList(channelsList);
//        putUpdatedData(channelsList);
//    }
}
