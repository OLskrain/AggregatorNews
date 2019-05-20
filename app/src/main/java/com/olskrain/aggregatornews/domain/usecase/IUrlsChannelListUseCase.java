package com.olskrain.aggregatornews.domain.usecase;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Andrey Ievlev on 20,Май,2019
 */

public interface IUrlsChannelListUseCase {
    Single<List<String>> getUrlsChannelList();

    void putUrlChannelsList(List<String> urlChannelsList);
}

