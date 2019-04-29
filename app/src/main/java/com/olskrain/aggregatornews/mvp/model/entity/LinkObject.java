package com.olskrain.aggregatornews.mvp.model.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey Ievlev on 27,Апрель,2019
 */

public class LinkObject {
    private List<String> linkList = new ArrayList<>();
    private static final String RSS_LINK = "https://news.yandex.ru/auto.rss";
    private static final String RSS_LINK2 = "https://news.yandex.ru/world.html?from=rss";
    private static final String RSS_LINK3 = "https://news.yandex.ru/gadgets.html?from=rss";

    //Todo: потом заменить на  добавление на кнопку
    public LinkObject() {
        addLink(RSS_LINK);
        addLink(RSS_LINK2);
        addLink(RSS_LINK3);
        addLink(RSS_LINK3);
        addLink(RSS_LINK3);
        addLink(RSS_LINK3);
        addLink(RSS_LINK3);
        addLink(RSS_LINK3);
        addLink(RSS_LINK3);
        addLink(RSS_LINK3);
        addLink(RSS_LINK3);
        addLink(RSS_LINK3);
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
