package com.olskrain.aggregatornews.data.repository.interfaceRepository;

import io.reactivex.Single;

/**
 * Created by Andrey Ievlev on 09,Июнь,2019
 */

public interface IBaseRepository {
    Single<Integer> getAppTheme();
}
