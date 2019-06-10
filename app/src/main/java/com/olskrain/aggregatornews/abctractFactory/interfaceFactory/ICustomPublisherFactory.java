package com.olskrain.aggregatornews.abctractFactory.interfaceFactory;

import com.olskrain.aggregatornews.Common.myObserver.ICustomPublisher;

/**
 * Created by Andrey Ievlev on 29,Май,2019
 */

public interface ICustomPublisherFactory {
    ICustomPublisher.IActionAboveList createActionAboveList();

    ICustomPublisher.IActionAppParameters createActionAppParameters();
}
