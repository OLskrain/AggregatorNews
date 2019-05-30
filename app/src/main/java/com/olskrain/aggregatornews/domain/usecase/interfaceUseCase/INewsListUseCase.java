package com.olskrain.aggregatornews.domain.usecase.interfaceUseCase;

import com.olskrain.aggregatornews.domain.entities.ItemNew;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Andrey Ievlev on 11,Май,2019
 */

public interface INewsListUseCase {
    Single<List<ItemNew>> refreshNewsList(String urlChannel);

    Single<String> getUrlChannel();

    void saveUrlChannel(String urlChannel);
}
