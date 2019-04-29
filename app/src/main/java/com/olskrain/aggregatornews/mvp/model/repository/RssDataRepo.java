package com.olskrain.aggregatornews.mvp.model.repository;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.olskrain.aggregatornews.Common.App;
import com.olskrain.aggregatornews.Common.NetworkStatus;
import com.olskrain.aggregatornews.mvp.model.cache.ICache;
import com.olskrain.aggregatornews.mvp.model.cache.RssDataCache;
import com.olskrain.aggregatornews.mvp.model.entity.LinkObject;
import com.olskrain.aggregatornews.mvp.model.service.DataDownloadService;

/**
 * Created by Andrey Ievlev on 27,Апрель,2019
 */
public class RssDataRepo {

    public interface ICallback {
        void callingBack(String s);
    }

    private ICache cache;
    private ICallback callback;
    private String result;

    public RssDataRepo() {
        cache = new RssDataCache();
    }

    public void registerCallBack(ICallback callback) {
        this.callback = callback;
    }


    public String getRssData() {
        if (NetworkStatus.isOnline()) {

            startService();

            // регистрируем BroadcastReceiver
            BroadcastReceiver brFromService = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    //Todo: сюда прилетели данные и мы потом должны их обработать
                    result = intent.getStringExtra(DataDownloadService.EXTRA_KEY_OUT);
                    callback.callingBack(result);
                }
            };

            IntentFilter intentFilter = new IntentFilter(DataDownloadService.ACTION_RESPONSE);
            intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
            App.getInstance().registerReceiver(brFromService, intentFilter);


        }

        return result;
    }

    private void startService() {
        //Todo:потом делать через кеш
        LinkObject rssLink = new LinkObject();

        Intent intentDataDownloadService = new Intent(App.getInstance(), DataDownloadService.class);
        App.getInstance().startService(intentDataDownloadService.putExtra("RssLinkList", rssLink.getLinkList().get(0)));
    }
}
