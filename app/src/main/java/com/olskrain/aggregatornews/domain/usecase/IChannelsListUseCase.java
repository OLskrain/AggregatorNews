package com.olskrain.aggregatornews.domain.usecase;

import com.olskrain.aggregatornews.Common.Command;
import com.olskrain.aggregatornews.domain.entities.Channel;
import com.olskrain.aggregatornews.domain.entities.Feed;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by Andrey Ievlev on 11,Май,2019
 */

public interface IChannelsListUseCase {
    Single<Feed> addNewChannel(Command command, String urlChannel);

    void deleteChannel(String url);

    List<Feed> deleteAllChannels(Command command, List<Feed> channelsList);

    Single<List<Feed>> getChannelsList(Command command, List<String> urlList);

    Completable checkDuplicate(String urlChannel, List<String> urlsList);

}
