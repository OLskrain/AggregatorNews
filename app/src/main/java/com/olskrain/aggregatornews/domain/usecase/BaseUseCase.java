package com.olskrain.aggregatornews.domain.usecase;

import com.olskrain.aggregatornews.abctractFactory.FactoryProvider;
import com.olskrain.aggregatornews.data.repository.interfaceRepository.IBaseRepository;
import com.olskrain.aggregatornews.domain.usecase.interfaceUseCase.IBaseUseCase;

import io.reactivex.Single;

/**
 * Created by Andrey Ievlev on 09,Июнь,2019
 */

public class BaseUseCase implements IBaseUseCase {

    private final IBaseRepository baseRepository = FactoryProvider.providerRepositoryFactory().createBaseRepository();

    @Override
    public Single<Integer> getAppTheme() {
        return baseRepository.getAppTheme();
    }
}
