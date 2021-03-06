package com.olskrain.aggregatornews.domain.usecase;

import com.olskrain.aggregatornews.abctractFactory.FactoryProvider;
import com.olskrain.aggregatornews.data.repository.interfaceRepository.IUrlsChannelListRepository;
import com.olskrain.aggregatornews.domain.usecase.interfaceUseCase.IUrlsChannelListUseCase;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Andrey Ievlev on 20,Май,2019
 */

public class UrlsChannelListUseCase implements IUrlsChannelListUseCase {
    private final IUrlsChannelListRepository urlsChannelListRepository = FactoryProvider.providerRepositoryFactory().createUrlsChannelListRepository();

    @Override
    public Single<List<String>> getUrlsChannelList() {
        return urlsChannelListRepository.getUrlsChannelList();
    }

    @Override
    public void saveUrlChannelsList(final List<String> urlsChannelList) {
        urlsChannelListRepository.putUrlsChannelsList(urlsChannelList);
    }

    @Override
    public List<String> addUrlChannel(final List<String> urlsChannelsList, final String urlChannel) {
        urlsChannelsList.add(urlChannel);
        return urlsChannelsList;
    }

    @Override
    public List<String> deleteUrlChannel(final List<String> urlsChannelsList, final String urlChannel) {
        for (int i = 0; i < urlsChannelsList.size(); i++) {
            if (urlChannel.equalsIgnoreCase(urlsChannelsList.get(i))) {
                urlsChannelsList.remove(i);
            }
        }
        return urlsChannelsList;
    }

    @Override
    public List<String> deleteAllUrlsChannel(final List<String> urlsChannelsList) {
        urlsChannelsList.clear();
        urlsChannelListRepository.putUrlsChannelsList(urlsChannelsList);
        return urlsChannelsList;
    }
}
