package com.olskrain.aggregatornews.data.cache;

import com.olskrain.aggregatornews.Common.Command;

/**
 * Created by Andrey Ievlev on 30,Апрель,2019
 */

public interface IChannelsListCache {
    void updateDatabase(Command command, String urlChannel);

    void getData();
}
