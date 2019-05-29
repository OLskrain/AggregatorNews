package com.olskrain.aggregatornews.abctractFactory.interfaceFactory;

import com.olskrain.aggregatornews.domain.usecase.interfaceUseCase.IAddChannelUseCase;
import com.olskrain.aggregatornews.domain.usecase.interfaceUseCase.IChannelsListUseCase;
import com.olskrain.aggregatornews.domain.usecase.interfaceUseCase.INewsDetailUseCase;
import com.olskrain.aggregatornews.domain.usecase.interfaceUseCase.INewsListUseCase;
import com.olskrain.aggregatornews.domain.usecase.interfaceUseCase.IUrlsChannelListUseCase;

/**
 * Created by Andrey Ievlev on 29,Май,2019
 */

public interface IUseCaseFactory {
    IAddChannelUseCase createAddChannelUseCase();

    IChannelsListUseCase createChannelsListUseCase();

    INewsDetailUseCase createNewsDetailUseCase();

    INewsListUseCase createNewsListUseCase();

    IUrlsChannelListUseCase createUrlsChannelListUseCase();
}
