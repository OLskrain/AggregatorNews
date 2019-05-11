package com.olskrain.aggregatornews.presentationn.ui.view.item;

/**
 * Created by Andrey Ievlev on 10,Май,2019
 */
public interface INewsListItemView {
    int getPos();

    void setTitle(String title);

    void setLastBuildDate(String lastBuildDate);

    void setDescription(String description);
}
