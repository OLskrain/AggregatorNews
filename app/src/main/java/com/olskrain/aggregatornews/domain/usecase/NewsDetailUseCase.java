package com.olskrain.aggregatornews.domain.usecase;

import com.olskrain.aggregatornews.abctractFactory.FactoryProvider;
import com.olskrain.aggregatornews.data.repository.interfaceRepositiry.INewsDetailRepository;
import com.olskrain.aggregatornews.data.repository.NewsDetailRepository;
import com.olskrain.aggregatornews.domain.usecase.interfaceUseCase.INewsDetailUseCase;

import io.reactivex.Single;

/**
 * Created by Andrey Ievlev on 11,Май,2019
 */

public class NewsDetailUseCase implements INewsDetailUseCase {

    private final INewsDetailRepository newsDetailRepository = FactoryProvider.providerRepositoryFactory().createNewsDetailRepository();

    @Override
    public Single<String> getWebPage(final String urlNews) {
        return newsDetailRepository.getWebPage(urlNews);
    }
}
