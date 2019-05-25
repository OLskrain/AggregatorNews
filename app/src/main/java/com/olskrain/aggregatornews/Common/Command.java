package com.olskrain.aggregatornews.Common;

/**
 * Created by Andrey Ievlev on 01,Май,2019
 */

public enum Command {
    ADD_CHANNEL, DELETE_CHANNEL, DELETE_ALL_CHANNELS,
    SHARE_CHANNEL, REFRESH_CHANNELS, ADD_FAVORITE,
    REFRESH_NEWS, REFRESH_URL,
    ERROR_MESSAGE, ERROR_CHECK_DUPLICATE, ERROR_DIFFERENT
}
