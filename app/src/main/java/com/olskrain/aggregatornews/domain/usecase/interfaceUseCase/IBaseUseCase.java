package com.olskrain.aggregatornews.domain.usecase.interfaceUseCase;

import io.reactivex.Single;

/**
 * Created by Andrey Ievlev on 09,Июнь,2019
 */

public interface IBaseUseCase {
    Single<Integer> getAppTheme();
}
