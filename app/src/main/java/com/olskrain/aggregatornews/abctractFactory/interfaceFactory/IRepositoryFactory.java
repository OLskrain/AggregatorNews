package com.olskrain.aggregatornews.abctractFactory.interfaceFactory;

import com.olskrain.aggregatornews.data.repository.interfaceRepositiry.IChannelsListRepository;
import com.olskrain.aggregatornews.data.repository.interfaceRepositiry.INewsDetailRepository;
import com.olskrain.aggregatornews.data.repository.interfaceRepositiry.INewsListRepository;
import com.olskrain.aggregatornews.data.repository.interfaceRepositiry.IUrlChannelRepository;
import com.olskrain.aggregatornews.data.repository.interfaceRepositiry.IUrlsChannelListRepository;

/**
 * Created by Andrey Ievlev on 29,Май,2019
 */

public interface IRepositoryFactory {
    IChannelsListRepository createChannelsListRepository();

    INewsDetailRepository createNewsDetailRepository();

    INewsListRepository createNewsListRepository();

    IUrlsChannelListRepository createUrlsChannelListRepository();

    IUrlChannelRepository createUrlChannelRepository();
}
