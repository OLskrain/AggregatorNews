package com.olskrain.aggregatornews.mvp.model.repository;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.olskrain.aggregatornews.Common.App;
import com.olskrain.aggregatornews.Common.NetworkStatus;
import com.olskrain.aggregatornews.mvp.model.cache.ICache;
import com.olskrain.aggregatornews.mvp.model.cache.RssDataCache;
import com.olskrain.aggregatornews.mvp.model.entity.RssLinkObject;
import com.olskrain.aggregatornews.mvp.model.service.DataDownloadService;

/**
 * Created by Andrey Ievlev on 27,Апрель,2019
 */
public class RssDataRepo {

    private ICache cache;
    private String result;

    public RssDataRepo() {
        cache = new RssDataCache();
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
                }
            };

            IntentFilter intentFilter = new IntentFilter(DataDownloadService.ACTION_RESPONSE);
            intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
            App.getInstance().registerReceiver(brFromService, intentFilter);


        }

        return result;
    }

    private void startService(){
        //Todo:потом делать через кеш
        RssLinkObject rssLink = new RssLinkObject();

        Intent intentDataDownloadService = new Intent(App.getInstance(), DataDownloadService.class);
        App.getInstance().startService(intentDataDownloadService.putExtra("RssLinkList", rssLink.getLinkList().get(0)));
    }

//    public boolean isMyServiceRunning() {
//        ActivityManager manager = (ActivityManager) App.getInstance().getSystemService(Context.ACTIVITY_SERVICE);
//        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
//            if (DataDownloadService.class.getName().equals(service.service.getClassName())) {
//                return true;
//            }
//        }
//        return false;
//    }
}
