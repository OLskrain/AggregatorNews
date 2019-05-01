package com.olskrain.aggregatornews.data.cache;

import com.olskrain.aggregatornews.domain.entities.ChannelsList;

/**
 * Created by Andrey Ievlev on 27,Апрель,2019
 */

public interface ILinksCache {
    void putLinks(ChannelsList links);

    ChannelsList getLinks();
}
