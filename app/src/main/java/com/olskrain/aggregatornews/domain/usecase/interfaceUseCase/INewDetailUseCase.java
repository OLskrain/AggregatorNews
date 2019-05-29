package com.olskrain.aggregatornews.domain.usecase.interfaceUseCase;

import io.reactivex.Single;

/**
 * Created by Andrey Ievlev on 11,Май,2019
 */

public interface INewDetailUseCase {
    Single<String> getWebPage(String urlNews);
}
