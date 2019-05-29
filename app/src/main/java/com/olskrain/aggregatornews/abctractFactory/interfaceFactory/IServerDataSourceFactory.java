package com.olskrain.aggregatornews.abctractFactory.interfaceFactory;

import com.olskrain.aggregatornews.data.api.IServerDataSource;

/**
 * Created by Andrey Ievlev on 29,Май,2019
 */

public interface IServerDataSourceFactory {
    IServerDataSource createServerDataSource();
}
