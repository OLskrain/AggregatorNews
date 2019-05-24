package com.olskrain.aggregatornews.data.api;

import com.olskrain.aggregatornews.domain.entities.Channel;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Andrey Ievlev on 24,Май,2019
 */

public interface IServerDataSource {
    Single<List<Channel>> getChannelFromApi(List<String> urlList);

    Single<String> getWebPage(String urlNews);
}
