package com.olskrain.aggregatornews.data.repository;

import java.util.List;

/**
 * Created by Andrey Ievlev on 30,Апрель,2019
 */

public interface IChannelsListRepository {
    String putChannelsList(List channelsList);

    List<String> getChannelsList();
}
