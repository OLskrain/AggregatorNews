package com.olskrain.aggregatornews.data.repository;

import com.olskrain.aggregatornews.domain.entities.Channel;
import com.olskrain.aggregatornews.domain.entities.Feed;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Andrey Ievlev on 30,Апрель,2019
 */

public interface IChannelsListRepository {
    void putUpdatedData(List<Channel> channelsList);

    Single<List<Feed>> getChannelsList(List<String> urlList);
}
