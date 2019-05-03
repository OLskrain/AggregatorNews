package com.olskrain.aggregatornews.data.repository;

import com.olskrain.aggregatornews.Common.Command;

/**
 * Created by Andrey Ievlev on 30,Апрель,2019
 */

public interface IChannelsListRepository {
    void putUpdatedData(Command command, String url);

    void getChannelsList();
}
