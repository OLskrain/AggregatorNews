package com.olskrain.aggregatornews.domain.usecase;

import com.olskrain.aggregatornews.data.repository.interfaceRepositiry.IUrlsChannelListRepository;
import com.olskrain.aggregatornews.data.repository.UrlListRepositoryRepository;
import com.olskrain.aggregatornews.domain.usecase.interfaceUseCase.IUrlsChannelListUseCase;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Andrey Ievlev on 20,Май,2019
 */

public class UrlsChannelListUseCase implements IUrlsChannelListUseCase {
    private IUrlsChannelListRepository urlsChannelListRepository;

    public UrlsChannelListUseCase() {
        this.urlsChannelListRepository = new UrlListRepositoryRepository();
    }

    @Override
    public Single<List<String>> getUrlsChannelList() {
        return urlsChannelListRepository.getUrlsChannelList();
    }

    @Override
    public void putUrlChannelsList(List<String> urlsChannelList) {
        urlsChannelListRepository.putUrlChannelsList(urlsChannelList);
    }

    @Override
    public List<String> addUrlChannel(List<String> urlsChannelsList, String urlChannel) {
        urlsChannelsList.add(urlChannel);
        return urlsChannelsList;
    }

    @Override
    public List<String> deleteUrlChannel(List<String> urlsChannelsList, String urlChannel) {
        for (int i = 0; i < urlsChannelsList.size(); i++) {
            if (urlChannel.equalsIgnoreCase(urlsChannelsList.get(i))) {
                urlsChannelsList.remove(i);
            }
        }
        return urlsChannelsList;
    }

    @Override
    public List<String> deleteAllUrlsChannel(List<String> urlsChannelsList) {
        urlsChannelsList.clear();
        return urlsChannelsList;
    }
}
