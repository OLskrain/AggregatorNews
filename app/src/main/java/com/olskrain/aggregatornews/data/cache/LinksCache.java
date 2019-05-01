package com.olskrain.aggregatornews.data.cache;

import com.olskrain.aggregatornews.domain.entities.ChannelsList;

/**
 * Created by Andrey Ievlev on 30,Апрель,2019
 */

public class LinksCache implements ILinksCache {

    @Override
    public void putLinks(ChannelsList links) {
        /**
         * запись в бд
         */
    }

    @Override
    public ChannelsList getLinks() {
        /**
         * тянем из в бд
         * и наполняем {@link ChannelsList}
         */
        return null;
    }
}
