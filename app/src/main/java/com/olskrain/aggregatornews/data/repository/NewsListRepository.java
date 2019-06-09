package com.olskrain.aggregatornews.data.repository;

import com.olskrain.aggregatornews.abctractFactory.FactoryProvider;
import com.olskrain.aggregatornews.data.cache.interfaceCache.INewsListCache;
import com.olskrain.aggregatornews.data.repository.interfaceRepository.INewsListRepository;
import com.olskrain.aggregatornews.domain.entities.ItemNew;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Andrey Ievlev on 10,Май,2019
 */

public class NewsListRepository implements INewsListRepository {
    private final INewsListCache cache = FactoryProvider.providerCacheFactory().createNewsListCache();

    @Override
    public Single<List<ItemNew>> getNewsList(final String urlChannel) {
       return cache.getNewsList(urlChannel);
    }
}
