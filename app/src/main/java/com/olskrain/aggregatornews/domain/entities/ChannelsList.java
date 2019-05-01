package com.olskrain.aggregatornews.domain.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey Ievlev on 27,Апрель,2019
 */

public class ChannelsList{
    private List<Channel> channelsList = new ArrayList<>();

//    private static final String RSS_LINK = "https://news.yandex.ru/auto.rss";
//    private static final String RSS_LINK2 = "https://news.yandex.ru/world.html?from=rss";
//    private static final String RSS_LINK3 = "https://news.yandex.ru/gadgets.html?from=rss";

    public List<Channel> getChannelsList() {
        return channelsList;
    }

    public void setChannelsList(List<Channel> channelsList) {
        this.channelsList = channelsList;
    }
}
