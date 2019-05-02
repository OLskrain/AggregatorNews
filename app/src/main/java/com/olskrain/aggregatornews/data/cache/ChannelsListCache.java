package com.olskrain.aggregatornews.data.cache;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey Ievlev on 27,Апрель,2019
 */

public class ChannelsListCache implements IChannelsListCache {
    List<String> vremenniyList = new ArrayList<>();


    @Override
    public String putData(List channelsList) {
        /**
         * идем в бд
         * незабыть про обратобку ошибок
         */
        return "Положили лист в БД";
    }

    @Override
    public List<String> getData() {
        /**
         * идем в бд
         * незабыть про обратобку ошибок
         */

        vremenniyList.add("https://news.yandex.ru/auto.html?from=rss");
        vremenniyList.add("https://news.yandex.ru/auto.html?from=rss");
        return vremenniyList;
    }
}
