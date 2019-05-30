package com.olskrain.aggregatornews.domain.usecase.interfaceUseCase;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Andrey Ievlev on 20,Май,2019
 */

public interface IUrlsChannelListUseCase {
    Single<List<String>> getUrlsChannelList();

    void saveUrlChannelsList(List<String> urlsChannelsList);

    List<String> addUrlChannel(List<String> urlsChannelsList, String urlChannel);

    List<String> deleteUrlChannel(List<String> urlsChannelsList, String urlChannel);

    List<String> deleteAllUrlsChannel(List<String> urlsChannelsList);
}

