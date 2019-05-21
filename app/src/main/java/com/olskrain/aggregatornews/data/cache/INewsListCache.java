package com.olskrain.aggregatornews.data.cache;

import com.olskrain.aggregatornews.domain.entities.ItemNew;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Andrey Ievlev on 21,Май,2019
 */

public interface INewsListCache {
    Single<List<ItemNew>> getNewsList (String urlChannel);
}
