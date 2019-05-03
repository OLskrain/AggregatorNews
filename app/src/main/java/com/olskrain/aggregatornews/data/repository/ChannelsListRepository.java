package com.olskrain.aggregatornews.data.repository;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.olskrain.aggregatornews.Common.App;
import com.olskrain.aggregatornews.Common.Command;
import com.olskrain.aggregatornews.data.cache.ChannelsListCache;
import com.olskrain.aggregatornews.data.cache.IChannelsListCache;
import com.olskrain.aggregatornews.data.service.DataDownloadService;
import com.olskrain.aggregatornews.domain.entities.Channel;

import java.util.List;

/**
 * Created by Andrey Ievlev on 27,Апрель,2019
 */

public class ChannelsListRepository implements IChannelsListRepository, ChannelsListCache.IResponseDBCallback {

    public interface IResponseDBCallback {
        void sendMessageStatusCallingBack(String message);
        void sendChannelsListCallingBack(List<String> channelsList);
    }

    private IChannelsListCache cache;
    private IResponseDBCallback callback;
    private String result;

    public ChannelsListRepository() {
        cache = new ChannelsListCache();
        ((ChannelsListCache) cache).registerCallBack(this);
    }

    public void registerCallBack(IResponseDBCallback callback) {
        this.callback = callback;
    }

    @Override
    public void putUpdatedData(Command command, String urlChannel) {
        cache.updateDatabase(command, urlChannel);
    }

    @Override
    public void getChannelsList() {
        cache.getData();
    }

    @Override
    public void sendMessageStatusCallingBack(String message) {
        callback.sendMessageStatusCallingBack(message);
    }

    @Override
    public void sendChannelsListCallingBack(List<String> channelsList) {
        callback.sendChannelsListCallingBack(channelsList);
    }

//    @Override
//    public Channel getData(ChannelsList links) {
//        if (NetworkStatus.isOnline()) {
//            //startService(links);
//            registerBroadcastReceiver();
//            //return cache.getChannel();
//            return null;
//        } else {
//            //return cache.getLinks(url);
//            return null;
//        }
//    }

//    private void startService(ChannelsList links) {
//        Intent intentDataDownloadService = new Intent(App.getInstance(), DataDownloadService.class);
//        App.getInstance().startService(intentDataDownloadService.putExtra("LinksList", links));
//    }

    private void registerBroadcastReceiver() {
        ResponseService responseService = new ResponseService();
        IntentFilter intentFilter = new IntentFilter(DataDownloadService.ACTION_RESPONSE);
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        App.getInstance().registerReceiver(responseService, intentFilter);

    }

    private class ResponseService extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //Todo: решить проблему с накапливание ответов
            result = intent.getStringExtra(DataDownloadService.EXTRA_KEY_OUT);

            /** тут мы отправляем ответ от сервера на парсинг в отдельный класс
             * Он возвашает нам объект {@link Channel}
             * Потом мы это кешируем в БД
             * cache.putLink({@link Channel});
             * А сам {@link Channel}
             * отправляем в презентер через callback.sendMessageStatusCallingBack({@link Channel});
             */

            // callback.callingBack(result);
        }
    }
}
