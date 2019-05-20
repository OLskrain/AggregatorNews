package com.olskrain.aggregatornews.data.repository;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Andrey Ievlev on 20,Май,2019
 */

public interface IUrlsChannelListRepository {
    Single<List<String>> getUrlsChannelList();

    void putUrlChannelsList(List<String> urlChannelsList);
}
