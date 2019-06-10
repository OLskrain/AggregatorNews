package com.olskrain.aggregatornews.Common.myObserver;

import com.olskrain.aggregatornews.Common.Command;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey Ievlev on 10,Июнь,2019
 */

public class ActionAboveAppParameters implements ICustomPublisher.IActionAppParameters {

    private final List<IActionAboveApplicationParametersCustomObserver> observers = new ArrayList<>();

    @Override
    public void subscribe(final IActionAboveApplicationParametersCustomObserver observer) {
        observers.add(observer);
    }

    @Override
    public void unsubscribe(final IActionAboveApplicationParametersCustomObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notify(final Command command) {
        for (IActionAboveApplicationParametersCustomObserver observer : observers)
            observer.actionAboveApplicationParameters(command);
    }
}
