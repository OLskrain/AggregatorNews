package com.olskrain.aggregatornews.data.repository;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.olskrain.aggregatornews.domain.entities.Channel;

/**
 * Created by Andrey Ievlev on 04,Май,2019
 */

public class ResponseServiceBroadcast extends BroadcastReceiver {
    private Channel channel;

    @Override
    public void onReceive(Context context, Intent intent) {
        //Todo: решить проблему с накапливание ответов
        //channel = intent.getParcelableExtra(DataDownloadService.EXTRA_KEY_OUT);

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
