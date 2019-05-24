package com.olskrain.aggregatornews.presentation.ui.view.item;

/**
 * Created by Andrey Ievlev on 10,Май,2019
 */
public interface INewsListItemView {
    int getPos();

    int getCurrentPosition();

    void setTitle(String title);

    void setLastBuildDate(String lastBuildDate);
}
