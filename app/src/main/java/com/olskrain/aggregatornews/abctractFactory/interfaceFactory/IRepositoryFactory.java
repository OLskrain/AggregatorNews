package com.olskrain.aggregatornews.abctractFactory.interfaceFactory;

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

public interface IRepositoryFactory {
    IChannelsListRepository createChannelsListRepository();

    INewsDetailRepository createNewsDetailRepository();

    INewsListRepository createNewsListRepository();

    IUrlsChannelListRepository createUrlsChannelListRepository();

    IUrlChannelRepository createUrlChannelRepository();

    ISettingsRepository createSettingsRepository();

    IBaseRepository createBaseRepository();
}
