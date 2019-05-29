package com.olskrain.aggregatornews.abctractFactory.factory;

import com.olskrain.aggregatornews.Common.IXmlRssParser;
import com.olskrain.aggregatornews.Common.XmlRssParser;
import com.olskrain.aggregatornews.abctractFactory.interfaceFactory.IXmlRssParserFactory;

/**
 * Created by Andrey Ievlev on 29,Май,2019
 */

public class XmlRssParserFactory implements IXmlRssParserFactory {

    @Override
    public IXmlRssParser createXmlRssParser() {
        return new XmlRssParser();
    }
}
