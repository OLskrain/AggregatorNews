package com.olskrain.aggregatornews.presentationn.ui.view;

/**
 * Created by Andrey Ievlev on 01,Май,2019
 */
public interface MainView {
    void addNewChannel();

    void showLoading();

    void hideLoading();

    void showText(String text);
}
