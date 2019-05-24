package com.olskrain.aggregatornews.domain.usecase;

import com.olskrain.aggregatornews.domain.entities.Feed;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by Andrey Ievlev on 20,Май,2019
 */

public interface IUrlsChannelListUseCase {
    Single<List<String>> getUrlsChannelList();

    void putUrlChannelsList(List<String> urlsChannelsList);

    List<String> addUrlChannel(List<String> urlsChannelsList, String urlChannel);

    List<String> deleteUrlChannel(List<String> urlsChannelsList, String urlChannel);

    List<String> deleteAllUrlsChannel(List<String> urlsChannelsList);
}

