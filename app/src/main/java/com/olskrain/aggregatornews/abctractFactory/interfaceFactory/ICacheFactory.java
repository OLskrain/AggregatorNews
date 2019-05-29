package com.olskrain.aggregatornews.abctractFactory.interfaceFactory;

import com.olskrain.aggregatornews.data.cache.interfaceCache.IChannelsListCache;
import com.olskrain.aggregatornews.data.cache.interfaceCache.INewsListCache;

/**
 * Created by Andrey Ievlev on 29,Май,2019
 */

public interface ICacheFactory {
    IChannelsListCache createChannelsListCache();

    INewsListCache createNewsListCache();
}
