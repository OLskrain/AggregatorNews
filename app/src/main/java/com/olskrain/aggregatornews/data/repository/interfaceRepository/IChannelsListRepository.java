package com.olskrain.aggregatornews.data.repository.interfaceRepository;

import com.olskrain.aggregatornews.Common.Command;
import com.olskrain.aggregatornews.domain.entities.Channel;
import com.olskrain.aggregatornews.domain.entities.Feed;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by Andrey Ievlev on 30,Апрель,2019
 */

public interface IChannelsListRepository {
    void putUpdatedData(Command command, List<Channel> channelsList);

    Single<List<Feed>> refreshChannelsList(Command command, List<String> urlList);

    Single<Feed> getChannel(Command command, List<String> urlList);

    Single<List<Feed>> getChannelListDB();

    Completable deleteAllChannels ();

    Completable deleteChannel (String urlChannel);
}
