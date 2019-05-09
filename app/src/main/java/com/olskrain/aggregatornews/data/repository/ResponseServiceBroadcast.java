package com.olskrain.aggregatornews.data.repository;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.olskrain.aggregatornews.domain.entities.Channel;

/**
 * Created by Andrey Ievlev on 04,Май,2019
 */

public class ResponseServiceBroadcast extends BroadcastReceiver {

    interface IResponseServerCallback {
        void sendChannelCallingBack(Channel channel);
    }

    private static final String EXTRA_KEY_OUT = "extra key out";
    private IResponseServerCallback callback;

    public void registerCallBack(IResponseServerCallback callback) {
        this.callback = callback;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Channel channel = intent.getParcelableExtra(EXTRA_KEY_OUT);
        callback.sendChannelCallingBack(channel);
    }
}
