package com.olskrain.aggregatornews.domain.usecase;

import android.annotation.SuppressLint;

import com.olskrain.aggregatornews.Common.Command;
import com.olskrain.aggregatornews.abctractFactory.FactoryProvider;
import com.olskrain.aggregatornews.data.repository.interfaceRepository.IChannelsListRepository;
import com.olskrain.aggregatornews.domain.entities.Feed;
import com.olskrain.aggregatornews.domain.usecase.interfaceUseCase.IChannelsListUseCase;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Andrey Ievlev on 01,Май,2019
 */

public class ChannelsListUseCase implements IChannelsListUseCase {

    private final IChannelsListRepository channelsListRepository = FactoryProvider.providerRepositoryFactory().createChannelsListRepository();

    @SuppressLint("CheckResult")
    @Override
    public Single<Feed> addNewChannel(final Command command, final String urlChannel) {
        List<String> urlList = new ArrayList<>();
        urlList.add(urlChannel);
        return channelsListRepository.getChannel(command, urlList);

    }

    @Override
    public Completable checkDuplicate(final String urlChannel, final List<String> urlsList) {
        return Completable.create(emitter -> {
            for (int i = 0; i < urlsList.size(); i++) {
                String incomingURL = urlChannel.replace("http://", "").replace("https://", "");
                String currentUrl = urlsList.get(i).replace("http://", "").replace("https://", "");

                if (incomingURL.equalsIgnoreCase(currentUrl)) {
                    emitter.onError(new RuntimeException());
                }
            }
            emitter.onComplete();
        }).subscribeOn(Schedulers.computation());
    }

    @Override
    public Completable deleteChannel(final String urlChannel) {
        return channelsListRepository.deleteChannel(urlChannel);
    }

    @Override
    public Completable deleteAllChannels() {
        return channelsListRepository.deleteAllChannels();
    }

    @Override
    public Single<List<Feed>> refreshChannelsList(final Command command, final List<String> urlList) {
        return channelsListRepository.refreshChannelsList(command, urlList);
    }

    @Override
    public Single<List<Feed>> getChannelListDB() {
        return channelsListRepository.getChannelListDB();
    }
}
