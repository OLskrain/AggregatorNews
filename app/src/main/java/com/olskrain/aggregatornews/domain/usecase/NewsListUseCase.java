package com.olskrain.aggregatornews.domain.usecase;

import com.olskrain.aggregatornews.abctractFactory.FactoryProvider;
import com.olskrain.aggregatornews.data.repository.interfaceRepositiry.INewsListRepository;
import com.olskrain.aggregatornews.data.repository.NewsListRepository;
import com.olskrain.aggregatornews.data.repository.interfaceRepositiry.IUrlChannelRepository;
import com.olskrain.aggregatornews.domain.entities.ItemNew;
import com.olskrain.aggregatornews.domain.usecase.interfaceUseCase.INewsListUseCase;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Andrey Ievlev on 10,Май,2019
 */

public class NewsListUseCase implements INewsListUseCase {

    private final INewsListRepository newsListRepository = FactoryProvider.providerRepositoryFactory().createNewsListRepository();
    private final IUrlChannelRepository urlChannelRepository = FactoryProvider.providerRepositoryFactory().createUrlChannelRepository();

    @Override
    public Single<List<ItemNew>> refreshNewsList(final String urlChannel) {
       return newsListRepository.getNewsList(urlChannel);
    }

    @Override
    public Single<String> getUrlChannel() {
        return urlChannelRepository.getUrlChannel();
    }

    @Override
    public void saveUrlChannel(String urlChannel) {
        urlChannelRepository.putUrlChannel(urlChannel);
    }
}
