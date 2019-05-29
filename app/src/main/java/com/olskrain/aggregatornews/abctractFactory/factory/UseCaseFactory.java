package com.olskrain.aggregatornews.abctractFactory.factory;

import com.olskrain.aggregatornews.abctractFactory.interfaceFactory.IUseCaseFactory;
import com.olskrain.aggregatornews.domain.usecase.AddChannelUseCase;
import com.olskrain.aggregatornews.domain.usecase.ChannelsListUseCase;
import com.olskrain.aggregatornews.domain.usecase.NewsDetailUseCase;
import com.olskrain.aggregatornews.domain.usecase.NewsListUseCase;
import com.olskrain.aggregatornews.domain.usecase.UrlsChannelListUseCase;
import com.olskrain.aggregatornews.domain.usecase.interfaceUseCase.IAddChannelUseCase;
import com.olskrain.aggregatornews.domain.usecase.interfaceUseCase.IChannelsListUseCase;
import com.olskrain.aggregatornews.domain.usecase.interfaceUseCase.INewsDetailUseCase;
import com.olskrain.aggregatornews.domain.usecase.interfaceUseCase.INewsListUseCase;
import com.olskrain.aggregatornews.domain.usecase.interfaceUseCase.IUrlsChannelListUseCase;

/**
 * Created by Andrey Ievlev on 29,Май,2019
 */

public class UseCaseFactory implements IUseCaseFactory {

    @Override
    public IAddChannelUseCase createAddChannelUseCase() {
        return new AddChannelUseCase();
    }

    @Override
    public IChannelsListUseCase createChannelsListUseCase() {
        return new ChannelsListUseCase();
    }

    @Override
    public INewsDetailUseCase createNewsDetailUseCase() {
        return new NewsDetailUseCase();
    }

    @Override
    public INewsListUseCase createNewsListUseCase() {
        return new NewsListUseCase();
    }

    @Override
    public IUrlsChannelListUseCase createUrlsChannelListUseCase() {
        return new UrlsChannelListUseCase();
    }
}
