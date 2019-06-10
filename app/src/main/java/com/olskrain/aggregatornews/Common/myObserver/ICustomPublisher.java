package com.olskrain.aggregatornews.Common.myObserver;

import com.olskrain.aggregatornews.Common.Command;

/**
 * Created by Andrey Ievlev on 29,Май,2019
 */

public interface ICustomPublisher {
    interface IActionAboveList {
        void subscribe(IActionAboveListCustomObserver observer);

        void unsubscribe(IActionAboveListCustomObserver observer);

        void notify(Command command);
    }

    interface IActionAppParameters {
        void subscribe(IActionAboveApplicationParametersCustomObserver observer);

        void unsubscribe(IActionAboveApplicationParametersCustomObserver observer);

        void notify(Command command);
    }
}
