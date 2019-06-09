package com.olskrain.aggregatornews.abctractFactory.factory;

import com.olskrain.aggregatornews.abctractFactory.interfaceFactory.IRepositoryFactory;
import com.olskrain.aggregatornews.data.repository.BaseRepository;
import com.olskrain.aggregatornews.data.repository.ChannelsListRepository;
import com.olskrain.aggregatornews.data.repository.NewsDetailRepository;
import com.olskrain.aggregatornews.data.repository.NewsListRepository;
import com.olskrain.aggregatornews.data.repository.SettingsRepository;
import com.olskrain.aggregatornews.data.repository.UrlChannelRepository;
import com.olskrain.aggregatornews.data.repository.UrlListChannelRepository;
import com.olskrain.aggregatornews.data.repository.interfaceRepository.IBaseRepository;
import com.olskrain.aggregatornews.data.repository.interfaceRepository.IChannelsListRepository;
import com.olskrain.aggregatornews.data.repository.interfaceRepository.INewsDetailRepository;
import com.olskrain.aggregatornews.data.repository.interfaceRepository.INewsListRepository;
import com.olskrain.aggregatornews.data.repository.interfaceRepository.ISettingsRepository;
import com.olskrain.aggregatornews.data.repository.interfaceRepository.IUrlChannelRepository;
import com.olskrain.aggregatornews.data.repository.interfaceRepository.IUrlsChannelListRepository;

/**
 * Created by Andrey Ievlev on 29,Май,2019
 */

public class RepositoryFactory implements IRepositoryFactory {

    @Override
    public IChannelsListRepository createChannelsListRepository() {
        return new ChannelsListRepository();
    }

    @Override
    public INewsDetailRepository createNewsDetailRepository() {
        return new NewsDetailRepository();
    }

    @Override
    public INewsListRepository createNewsListRepository() {
        return new NewsListRepository();
    }

    @Override
    public IUrlsChannelListRepository createUrlsChannelListRepository() {
        return new UrlListChannelRepository();
    }

    @Override
    public IUrlChannelRepository createUrlChannelRepository() {
        return new UrlChannelRepository();
    }

    @Override
    public ISettingsRepository createSettingsRepository() {
        return new SettingsRepository();
    }

    @Override
    public IBaseRepository createBaseRepository() {
        return new BaseRepository();
    }
}
