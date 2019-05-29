package com.olskrain.aggregatornews.Common.myObserver;

import com.olskrain.aggregatornews.Common.Command;

/**
 * Created by Andrey Ievlev on 29,Май,2019
 */

public interface ICustomPublisher {
    void subscribe(ICustomObserver observer);

    void unsubscribe(ICustomObserver observer);

    void notify(Command command);
}
