package com.olskrain.aggregatornews.abctractFactory.interfaceFactory;

import com.olskrain.aggregatornews.Common.IXmlRssParser;

/**
 * Created by Andrey Ievlev on 29,Май,2019
 */

public interface IXmlRssParserFactory {
    IXmlRssParser createXmlRssParser();
}
