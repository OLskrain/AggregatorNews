package com.olskrain.aggregatornews.domain.usecase;

import android.annotation.SuppressLint;

import com.olskrain.aggregatornews.Common.Command;
import com.olskrain.aggregatornews.data.repository.ChannelsListRepository;
import com.olskrain.aggregatornews.data.repository.IChannelsListRepository;
import com.olskrain.aggregatornews.domain.entities.Channel;
import com.olskrain.aggregatornews.domain.entities.Feed;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by Andrey Ievlev on 01,Май,2019
 */

public class ChannelsListUseCase implements IChannelsListUseCase {

    private IChannelsListRepository channelRepository;

    public ChannelsListUseCase() {
        this.channelRepository = new ChannelsListRepository();
    }

    @SuppressLint("CheckResult")
    @Override
    public Single<Feed> addNewChannel(Command command, String urlChannel) {
        List<String> urlList = new ArrayList<>();
        urlList.add(urlChannel);
        return channelRepository.getChannel(command, urlList);

    }

    @Override
    public Completable checkDuplicate(String urlChannel, List<String> urlsList) {
        return Completable.create(emitter -> {
            for (int i = 0; i < urlsList.size(); i++) {
                if (urlChannel.equalsIgnoreCase(urlsList.get(i))) {
                    emitter.onError(new RuntimeException());
                }
            }
            emitter.onComplete();
        }).subscribeOn(Schedulers.computation());
    }

    @Override
    public void deleteChannel(String url) {

    }

//    @Override
//    public void deleteChannel(List<Channel> channelsList, int position) {
//        channelsList.remove(position);
//
//    }

    @Override
    public List<Feed> deleteAllChannels(Command command, List<Feed> channelList) {
        channelRepository.putUpdatedData(command, null);
        channelList.clear();
        return channelList;
    }

    @Override
    public Single<List<Feed>> getChannelsList(Command command, List<String> urlList) {
        return channelRepository.getChannelsList(command, urlList);
    }
}
