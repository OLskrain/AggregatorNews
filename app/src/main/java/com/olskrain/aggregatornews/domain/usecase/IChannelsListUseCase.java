package com.olskrain.aggregatornews.domain.usecase;

/**
 * Created by Andrey Ievlev on 11,Май,2019
 */
public interface IChannelsListUseCase {
    void addNewChannel(String urlChannel);

    void deleteChannel(int position);

    void deleteAllChannels();

    void refreshChannelsList();

    void putChannelsList();

    void registerCallBack(ChannelsListUseCase.IResponseDBCallback callback);
}
