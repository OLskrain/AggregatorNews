package com.olskrain.aggregatornews.data.cache;

import com.olskrain.aggregatornews.Common.Command;
import com.olskrain.aggregatornews.domain.entities.Channel;

import java.util.List;

/**
 * Created by Andrey Ievlev on 30,Апрель,2019
 */

public interface IChannelsListCache {
    void updateDatabase(Command command, List<Channel> channelsList);

    void getData();
}
