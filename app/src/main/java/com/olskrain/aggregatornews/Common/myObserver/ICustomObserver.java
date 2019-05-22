package com.olskrain.aggregatornews.Common.myObserver;

import com.olskrain.aggregatornews.Common.Command;

/**
 * Created by Andrey Ievlev on 22,Май,2019
 */

public interface ICustomObserver {
    void actionAboveChannelsList(Command command);
}
