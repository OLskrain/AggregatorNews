package com.olskrain.aggregatornews.data.repository;

import com.olskrain.aggregatornews.data.cache.ILinksCache;
import com.olskrain.aggregatornews.data.cache.LinksCache;
import com.olskrain.aggregatornews.domain.entities.ChannelsList;

/**
 * Created by Andrey Ievlev on 30,Апрель,2019
 */

public class LinksRepository implements ILinksRepository {

    private ILinksCache cache;

    public LinksRepository() {
        this.cache = new LinksCache();
    }

    @Override
    public ChannelsList getData() {
        /**
         * получаем данные из БД или от куда то еще
         */
        return cache.getLinks();
    }

    @Override
    public void putData(ChannelsList links) {
        /**
         * обновляем данные в БД
         */
        cache.putLinks(links);
    }
}
