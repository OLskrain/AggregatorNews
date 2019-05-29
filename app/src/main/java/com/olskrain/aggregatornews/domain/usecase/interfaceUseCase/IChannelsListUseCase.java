package com.olskrain.aggregatornews.domain.usecase.interfaceUseCase;

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

    Single<List<Feed>> refreshChannelsList(Command command, List<String> urlList);

    Single<List<Feed>> getChannelListDB();

    Completable deleteChannel(String urlChannel);

    Completable deleteAllChannels();

    Completable checkDuplicate(String urlChannel, List<String> urlsList);
}
