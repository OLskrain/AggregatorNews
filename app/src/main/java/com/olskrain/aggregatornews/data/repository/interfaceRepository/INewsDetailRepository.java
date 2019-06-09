package com.olskrain.aggregatornews.data.repository.interfaceRepository;

import io.reactivex.Single;

/**
 * Created by Andrey Ievlev on 10,Май,2019
 */

public interface INewsDetailRepository {
    Single<String> getWebPage(String urlNews);
}
