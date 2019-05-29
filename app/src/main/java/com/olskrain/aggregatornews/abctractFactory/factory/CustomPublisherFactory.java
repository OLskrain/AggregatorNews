package com.olskrain.aggregatornews.abctractFactory.factory;

import com.olskrain.aggregatornews.Common.myObserver.CustomPublisher;
import com.olskrain.aggregatornews.Common.myObserver.ICustomPublisher;
import com.olskrain.aggregatornews.abctractFactory.interfaceFactory.ICustomPublisherFactory;

/**
 * Created by Andrey Ievlev on 29,Май,2019
 */

public class CustomPublisherFactory implements ICustomPublisherFactory {

    @Override
    public ICustomPublisher createCustomPublisher() {
        return new CustomPublisher();
    }
}
