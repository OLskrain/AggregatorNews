package com.olskrain.aggregatornews.Common;

import com.olskrain.aggregatornews.domain.entities.Channel;

/**
 * Created by Andrey Ievlev on 29,Май,2019
 */

public interface IXmlRssParser {
    Channel parseData(String urlChannel, String responseServer);
}
