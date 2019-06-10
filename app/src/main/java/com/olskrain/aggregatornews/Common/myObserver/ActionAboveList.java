package com.olskrain.aggregatornews.Common.myObserver;

import com.olskrain.aggregatornews.Common.Command;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey Ievlev on 22,Май,2019
 */

public class ActionAboveList implements ICustomPublisher.IActionAboveList {

    private final List<IActionAboveListCustomObserver> observers = new ArrayList<>();

    @Override
    public void subscribe(final IActionAboveListCustomObserver observer) {
        observers.add(observer);
    }

    @Override
    public void unsubscribe(final IActionAboveListCustomObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notify(final Command command) {
        for (IActionAboveListCustomObserver observer : observers)
            observer.actionAboveChannelsList(command);
    }
}
