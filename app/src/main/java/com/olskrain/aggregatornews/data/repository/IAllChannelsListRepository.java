package com.olskrain.aggregatornews.data.repository;

import com.olskrain.aggregatornews.domain.entities.Channel;

import java.util.List;

/**
 * Created by Andrey Ievlev on 30,Апрель,2019
 */

public interface IAllChannelsListRepository {
    void putUpdatedData(List<Channel> channelsList);

    void getChannelsList();

    void getChannel(String url);
}
