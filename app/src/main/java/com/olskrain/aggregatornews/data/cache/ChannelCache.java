package com.olskrain.aggregatornews.data.cache;

import java.util.List;

/**
 * Created by Andrey Ievlev on 27,Апрель,2019
 */

public class ChannelCache implements IChannelCache {

    @Override
    public String putData(List channelsList) {
        /**
         * идем в бд
         */
        return "Обновили БД";
    }
}
