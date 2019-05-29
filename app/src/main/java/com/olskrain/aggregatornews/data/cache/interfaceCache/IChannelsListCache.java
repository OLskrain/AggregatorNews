package com.olskrain.aggregatornews.data.cache.interfaceCache;

import com.olskrain.aggregatornews.Common.Command;
import com.olskrain.aggregatornews.domain.entities.Channel;
import com.olskrain.aggregatornews.domain.entities.Feed;
import com.olskrain.aggregatornews.domain.entities.ItemNew;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by Andrey Ievlev on 30,Апрель,2019
 */

public interface IChannelsListCache {
    void updateDatabase(Command command, List<Channel> channelsList);

    Single<List<Feed>> getChannelsList();

    Completable deleteAllChannels ();

    Completable deleteChannel (String urlChannel);
}
