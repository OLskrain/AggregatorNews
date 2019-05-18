package com.olskrain.aggregatornews.domain.usecase;

import android.annotation.SuppressLint;

import com.olskrain.aggregatornews.data.repository.ChannelsListRepository;
import com.olskrain.aggregatornews.data.repository.IChannelsListRepository;
import com.olskrain.aggregatornews.domain.entities.Channel;
import com.olskrain.aggregatornews.domain.entities.Feed;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by Andrey Ievlev on 01,Май,2019
 */

public class ChannelsListUseCase implements IChannelsListUseCase {

    private IChannelsListRepository channelRepository;

    public ChannelsListUseCase() {
        this.channelRepository = new ChannelsListRepository();
    }

    @SuppressLint("CheckResult")
    @Override
    public Single<List<Feed>> addNewChannel(String urlChannel) {
        List<String> urlList = new ArrayList<>();
        urlList.add(urlChannel);
       return channelRepository.getChannelsList(urlList).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void deleteChannel(List<Channel> channelsList, int position) {
        channelsList.remove(position);

    }

    @Override
    public void deleteAllChannels(List<Channel> channelsList) {
        channelsList.clear();

    }

//    @Override
//    public void refreshChannelsList() {
//        channelRepository.getChannelsList();
//    }

    @Override
    public void putChannelsList(List<Channel> channelsList) {
        channelRepository.putUpdatedData(channelsList);
    }

//    @Override
//    public void onMessageStatus(String message) {
//        callback.onMessageStatus(message);
//    }
//
//    @Override
//    public void onChannelsList(List<Channel> channelsList) {
//        callback.onChannelsList(channelsList);
//    }
}
