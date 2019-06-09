package com.olskrain.aggregatornews.data.repository;

import com.olskrain.aggregatornews.Common.NetworkStatus;
import com.olskrain.aggregatornews.abctractFactory.FactoryProvider;
import com.olskrain.aggregatornews.data.api.IServerDataSource;
import com.olskrain.aggregatornews.data.repository.interfaceRepository.INewsDetailRepository;

import io.reactivex.Single;

/**
 * Created by Andrey Ievlev on 11,Май,2019
 */

public class NewsDetailRepository implements INewsDetailRepository {
    private final IServerDataSource serverDataSource = FactoryProvider.providerServerDataSourceFactory().createServerDataSource();

    @Override
    public Single<String> getWebPage(final String urlNews) {
        if (NetworkStatus.isOnline()) {
            return serverDataSource.getWebPage(urlNews);
        } else {
            return Single.error(new RuntimeException());
        }
    }
}
