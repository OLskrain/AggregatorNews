package com.olskrain.aggregatornews.domain.usecase;

import com.olskrain.aggregatornews.abctractFactory.FactoryProvider;
import com.olskrain.aggregatornews.data.repository.interfaceRepositiry.INewsListRepository;
import com.olskrain.aggregatornews.data.repository.NewsListRepository;
import com.olskrain.aggregatornews.domain.entities.ItemNew;
import com.olskrain.aggregatornews.domain.usecase.interfaceUseCase.INewsListUseCase;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Andrey Ievlev on 10,Май,2019
 */

public class NewsListUseCase implements INewsListUseCase {

    private final INewsListRepository newsListRepository = FactoryProvider.providerRepositoryFactory().createNewsListRepository();

    @Override
    public Single<List<ItemNew>> refreshNewsList(final String urlChannel) {
       return newsListRepository.getNewsList(urlChannel);
    }
}
