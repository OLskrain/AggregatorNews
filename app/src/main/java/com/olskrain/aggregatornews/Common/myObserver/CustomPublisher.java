package com.olskrain.aggregatornews.Common.myObserver;

import com.olskrain.aggregatornews.Common.Command;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey Ievlev on 22,Май,2019
 */

public class CustomPublisher {
    private final List<ICustomObserver> observers;

    public CustomPublisher() {
        observers = new ArrayList<>();
    }

    public void subscribe(final ICustomObserver observer) {
        observers.add(observer);
    }

    public void unsubscribe(final ICustomObserver observer) {
        observers.remove(observer);
    }

    public void notify(final Command command) {
        for (ICustomObserver observer : observers)
            observer.actionAboveChannelsList(command);
    }
}
