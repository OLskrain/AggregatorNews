package com.olskrain.aggregatornews.presentation.ui.view.item;

/**
 * Created by Andrey Ievlev on 29,Апрель,2019
 */

public interface IChannelListItemView {

    int getPos();

    void setTitle(String title);
    void setLastBuildDate(String lastBuildDate);
}