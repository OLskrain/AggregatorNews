package com.olskrain.aggregatornews.domain.usecase;

import com.olskrain.aggregatornews.data.repository.IUrlsChannelListRepository;
import com.olskrain.aggregatornews.data.repository.UrlListRepositoryRepository;
import com.olskrain.aggregatornews.domain.entities.Feed;

import java.util.ArrayList;
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
    public List<String> updateUrlsChannelList(List<Feed> urlList) {
        List<String> currentUrlsList = new ArrayList<>();
        for (int i = 0; i < urlList.size(); i++) {
            currentUrlsList.add(urlList.get(i).getUrl());
        }
        return currentUrlsList;
    }
}
