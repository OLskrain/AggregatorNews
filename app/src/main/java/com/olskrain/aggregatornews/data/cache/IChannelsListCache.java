package com.olskrain.aggregatornews.data.cache;

import java.util.List;

/**
 * Created by Andrey Ievlev on 30,Апрель,2019
 */
public interface IChannelsListCache {
    String putData(List channelsList);

    List<String> getData();
}
