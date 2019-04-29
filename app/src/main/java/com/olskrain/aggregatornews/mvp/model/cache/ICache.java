package com.olskrain.aggregatornews.mvp.model.cache;

import com.olskrain.aggregatornews.mvp.model.entity.LinkObject;

/**
 * Created by Andrey Ievlev on 27,Апрель,2019
 */

public interface ICache {
    void putRssData(LinkObject rssDataLink);

    void getRssData();
}
