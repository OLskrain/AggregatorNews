package com.olskrain.aggregatornews.data.repository;

import com.olskrain.aggregatornews.data.cache.interfaceCache.INewsListCache;
import com.olskrain.aggregatornews.data.cache.NewsListCache;
import com.olskrain.aggregatornews.data.repository.interfaceRepositiry.INewsListRepository;
import com.olskrain.aggregatornews.domain.entities.ItemNew;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Andrey Ievlev on 10,Май,2019
 */

public class NewsListRepository implements INewsListRepository {
    private INewsListCache cache;

    public NewsListRepository() {
        this.cache = new NewsListCache();
    }

    @Override
    public Single<List<ItemNew>> getNewsList(final String urlChannel) {
       return cache.getNewsList(urlChannel);
    }
}
