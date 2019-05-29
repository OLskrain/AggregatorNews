package com.olskrain.aggregatornews.abctractFactory.factory;

import com.olskrain.aggregatornews.abctractFactory.interfaceFactory.ICacheFactory;
import com.olskrain.aggregatornews.data.cache.ChannelsListCache;
import com.olskrain.aggregatornews.data.cache.NewsListCache;
import com.olskrain.aggregatornews.data.cache.interfaceCache.IChannelsListCache;
import com.olskrain.aggregatornews.data.cache.interfaceCache.INewsListCache;

/**
 * Created by Andrey Ievlev on 29,Май,2019
 */

public class CacheFactory implements ICacheFactory {

    @Override
    public IChannelsListCache createChannelsListCache() {
        return new ChannelsListCache();
    }

    @Override
    public INewsListCache createNewsListCache() {
        return new NewsListCache();
    }
}
