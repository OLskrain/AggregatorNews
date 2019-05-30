package com.olskrain.aggregatornews.data.repository.interfaceRepositiry;

import io.reactivex.Single;

/**
 * Created by Andrey Ievlev on 30,Май,2019
 */

public interface IUrlChannelRepository {
    Single<String> getUrlChannel();

    void putUrlChannel(String urlChannel);
}
