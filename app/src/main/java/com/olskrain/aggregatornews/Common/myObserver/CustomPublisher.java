package com.olskrain.aggregatornews.Common.myObserver;

import com.olskrain.aggregatornews.Common.Command;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey Ievlev on 22,Май,2019
 */

public class CustomPublisher implements ICustomPublisher {
    private final List<ICustomObserver> observers = new ArrayList<>();

    @Override
    public void subscribe(final ICustomObserver observer) {
        observers.add(observer);
    }

    @Override
    public void unsubscribe(final ICustomObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notify(final Command command) {
        for (ICustomObserver observer : observers)
            observer.actionAboveChannelsList(command);
    }
}
