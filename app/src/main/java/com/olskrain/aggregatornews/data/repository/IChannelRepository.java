package com.olskrain.aggregatornews.data.repository;

import com.olskrain.aggregatornews.domain.entities.Channel;
import com.olskrain.aggregatornews.domain.entities.ChannelsList;

/**
 * Created by Andrey Ievlev on 30,Апрель,2019
 */

public interface IChannelRepository {
    Channel getData(ChannelsList links);
}
