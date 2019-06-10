package com.olskrain.aggregatornews.domain.usecase;

import com.olskrain.aggregatornews.abctractFactory.FactoryProvider;
import com.olskrain.aggregatornews.data.repository.interfaceRepository.INewsListRepository;
import com.olskrain.aggregatornews.data.repository.interfaceRepository.IUrlChannelRepository;
import com.olskrain.aggregatornews.domain.entities.ItemNew;
import com.olskrain.aggregatornews.domain.usecase.interfaceUseCase.INewsListUseCase;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

import io.reactivex.Single;
import timber.log.Timber;

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

    @Override
    public Single<ItemNew> getRandomNews(List<ItemNew> newsList) {
        return Single.fromCallable(() -> {
            int min = 0;
            int max = newsList.size();
            int diff = max - min;

            Random random = new Random();
            int i = random.nextInt(diff + 1);
            i += min;

            return newsList.get(i);
        });
    }
}
