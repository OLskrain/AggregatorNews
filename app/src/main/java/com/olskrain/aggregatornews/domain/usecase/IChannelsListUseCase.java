package com.olskrain.aggregatornews.domain.usecase;

import com.olskrain.aggregatornews.domain.entities.Channel;
import com.olskrain.aggregatornews.domain.entities.Feed;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Andrey Ievlev on 11,Май,2019
 */
public interface IChannelsListUseCase {
    Single<List<Feed>> addNewChannel(String urlChannel);

    void deleteChannel(List<Channel> channelsList, int position);

    void deleteAllChannels(List<Channel> channelsList);

   // void refreshChannelsList();

    void putChannelsList(List<Channel> channelsList);

}
