package com.olskrain.aggregatornews.data.repository;

import com.olskrain.aggregatornews.Common.Command;
import com.olskrain.aggregatornews.Common.NetworkStatus;
import com.olskrain.aggregatornews.abctractFactory.FactoryProvider;
import com.olskrain.aggregatornews.data.api.IServerDataSource;
import com.olskrain.aggregatornews.data.cache.interfaceCache.IChannelsListCache;
import com.olskrain.aggregatornews.data.repository.interfaceRepository.IChannelsListRepository;
import com.olskrain.aggregatornews.domain.entities.Channel;
import com.olskrain.aggregatornews.domain.entities.Feed;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Andrey Ievlev on 27,Апрель,2019
 */

public class ChannelsListRepository implements IChannelsListRepository {
    private final IChannelsListCache cache = FactoryProvider.providerCacheFactory().createChannelsListCache();
    private final IServerDataSource serverDataSource = FactoryProvider.providerServerDataSourceFactory().createServerDataSource();
    private final List<Feed> channelsList = new ArrayList<>();
    private boolean fistUpdate = true;

    @Override
    public void putUpdatedData(final Command command, final List<Channel> channelsList) {
        cache.updateDatabase(command, channelsList);
    }

    @Override
    public Single<List<Feed>> refreshChannelsList(final Command command, final List<String> urlList) {
        if (NetworkStatus.isOnline()) {
            return serverDataSource.getChannelFromApi(urlList).subscribeOn(Schedulers.io())
                    .map(channelsList -> {
                        putUpdatedData(command, channelsList);

                        if (!fistUpdate){
                         this.channelsList.clear();
                        }
                        for (int i = 0; i < channelsList.size(); i++) {
                            this.channelsList.add(channelsList.get(i).getFeed());
                        }
                        fistUpdate = false;
                        return this.channelsList;
                    });
        } else {
            return cache.getChannelsList();
        }
    }

    @Override
    public Single<Feed> getChannel(final Command command, final List<String> urlList) {
        if (NetworkStatus.isOnline()) {
            return serverDataSource.getChannelFromApi(urlList).subscribeOn(Schedulers.io())
                    .map(channelsList -> {
                        putUpdatedData(command, channelsList);
                        return channelsList.get(channelsList.size() - 1).getFeed();
                    });
        } else {
            return Single.error(new RuntimeException());
        }
    }

    @Override
    public Single<List<Feed>> getChannelListDB() {
        return cache.getChannelsList();
    }

    @Override
    public Completable deleteAllChannels() {
        return cache.deleteAllChannels();
    }

    @Override
    public Completable deleteChannel(final String urlChannel) {
        return cache.deleteChannel(urlChannel);
    }
}
