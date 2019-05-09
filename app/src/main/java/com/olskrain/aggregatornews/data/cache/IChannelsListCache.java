package com.olskrain.aggregatornews.data.cache;

import com.olskrain.aggregatornews.domain.entities.Channel;

import java.util.List;

/**
 * Created by Andrey Ievlev on 30,Апрель,2019
 */

public interface IChannelsListCache {
    void updateDatabase(List<Channel> channelsList);

    void getData();
}
