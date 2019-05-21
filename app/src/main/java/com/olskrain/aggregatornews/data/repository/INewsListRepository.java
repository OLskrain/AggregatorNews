package com.olskrain.aggregatornews.data.repository;

import com.olskrain.aggregatornews.domain.entities.ItemNew;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Andrey Ievlev on 10,Май,2019
 */

public interface INewsListRepository {
    Single<List<ItemNew>> getNewsList(String urlChannel);
}
