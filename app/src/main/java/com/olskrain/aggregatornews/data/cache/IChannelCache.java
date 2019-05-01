package com.olskrain.aggregatornews.data.cache;

import com.olskrain.aggregatornews.domain.entities.Channel;

/**
 * Created by Andrey Ievlev on 30,Апрель,2019
 */
public interface IChannelCache {
    void putChannel(Channel channel);

    Channel getChannel(String url);
}
