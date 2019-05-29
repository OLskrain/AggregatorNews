package com.olskrain.aggregatornews.abctractFactory;

import com.olskrain.aggregatornews.abctractFactory.factory.CacheFactory;
import com.olskrain.aggregatornews.abctractFactory.factory.CustomPublisherFactory;
import com.olskrain.aggregatornews.abctractFactory.factory.DBHelperFactory;
import com.olskrain.aggregatornews.abctractFactory.factory.PresenterFactory;
import com.olskrain.aggregatornews.abctractFactory.factory.RepositoryFactory;
import com.olskrain.aggregatornews.abctractFactory.factory.ServerDataSourceFactory;
import com.olskrain.aggregatornews.abctractFactory.factory.UseCaseFactory;
import com.olskrain.aggregatornews.abctractFactory.factory.XmlRssParserFactory;
import com.olskrain.aggregatornews.abctractFactory.interfaceFactory.ICacheFactory;
import com.olskrain.aggregatornews.abctractFactory.interfaceFactory.ICustomPublisherFactory;
import com.olskrain.aggregatornews.abctractFactory.interfaceFactory.IDBHelperFactory;
import com.olskrain.aggregatornews.abctractFactory.interfaceFactory.IPresenterFactory;
import com.olskrain.aggregatornews.abctractFactory.interfaceFactory.IRepositoryFactory;
import com.olskrain.aggregatornews.abctractFactory.interfaceFactory.IServerDataSourceFactory;
import com.olskrain.aggregatornews.abctractFactory.interfaceFactory.IUseCaseFactory;
import com.olskrain.aggregatornews.abctractFactory.interfaceFactory.IXmlRssParserFactory;

/**
 * Created by Andrey Ievlev on 29,Май,2019
 */

public class FactoryProvider {

    public static IXmlRssParserFactory providerXmlRssParserFactory() {
        return new XmlRssParserFactory();
    }

    public static IServerDataSourceFactory providerServerDataSourceFactory() {
        return new ServerDataSourceFactory();
    }

    public static ICacheFactory providerCacheFactory() {
        return new CacheFactory();
    }

    public static IDBHelperFactory providerDBHelperFactory() {
        return new DBHelperFactory();
    }

    public static ICustomPublisherFactory providerCustomPublisherFactory() {
        return new CustomPublisherFactory();
    }

    public static IRepositoryFactory providerRepositoryFactory() {
        return new RepositoryFactory();
    }

    public static IUseCaseFactory providerUseCaseFactory() {
        return new UseCaseFactory();
    }

    public static IPresenterFactory providerPresenterFactory() {
        return new PresenterFactory();
    }
}
