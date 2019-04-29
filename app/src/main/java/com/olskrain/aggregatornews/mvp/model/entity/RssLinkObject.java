package com.olskrain.aggregatornews.mvp.model.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey Ievlev on 27,Апрель,2019
 */

public class RssLinkObject {
    private List<String> linkList = new ArrayList<>();
    private static final String RSS_LINK = "https://news.yandex.ru/auto.rss";

    //Todo: потом заменить на  добавление на кнопку
    public RssLinkObject() {
        addLink(RSS_LINK);
    }

    public List<String> getLinkList() {
        return linkList;
    }

    public void addLink(String url) {
        linkList.add(url);
    }

    public void deleteLink(String url) {
        linkList.remove(url);
    }

    public void deleteAll() {
        linkList.clear();
    }
}
