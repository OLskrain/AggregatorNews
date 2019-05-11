package com.olskrain.aggregatornews.data.repository;

import com.olskrain.aggregatornews.data.cache.ChannelsListCache;
import com.olskrain.aggregatornews.data.cache.IChannelsListCache;
import com.olskrain.aggregatornews.domain.entities.Channel;
import com.olskrain.aggregatornews.domain.entities.ItemNew;

import java.util.List;

/**
 * Created by Andrey Ievlev on 10,Май,2019
 */

public class NewsListRepository implements INewsListRepository, ChannelsListCache.IResponseDBCallback {

    public interface IResponseDBCallback {
        void sendMessageStatusCallingBack(String message);
        void sendNewsListCallingBack(List<ItemNew> newsList);
    }

    private IResponseDBCallback callback;
    private IChannelsListCache cache;
    private int channelPosition;

    public void registerCallBack(IResponseDBCallback callback) {
        this.callback = callback;
    }

    public NewsListRepository(int channelPosition) {
        this.channelPosition = channelPosition;
        this.cache = new ChannelsListCache();
        ((ChannelsListCache) cache).registerCallBack(this);
    }

    @Override
    public void getChannelsList() {
        cache.getData();
    }

    @Override
    public void sendMessageStatusCallingBack(String message) {

    }

    @Override
    public void sendChannelsListCallingBack(List<Channel> channelsList) {
        callback.sendNewsListCallingBack(channelsList.get(channelPosition).getItemNew());
    }
}