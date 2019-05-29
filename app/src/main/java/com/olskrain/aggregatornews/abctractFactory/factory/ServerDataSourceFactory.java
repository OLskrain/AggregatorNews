package com.olskrain.aggregatornews.abctractFactory.factory;

import com.olskrain.aggregatornews.abctractFactory.interfaceFactory.IServerDataSourceFactory;
import com.olskrain.aggregatornews.data.api.IServerDataSource;
import com.olskrain.aggregatornews.data.api.ServerDataSource;

/**
 * Created by Andrey Ievlev on 29,Май,2019
 */

public class ServerDataSourceFactory implements IServerDataSourceFactory {

    @Override
    public IServerDataSource createServerDataSource() {
        return new ServerDataSource();
    }
}
