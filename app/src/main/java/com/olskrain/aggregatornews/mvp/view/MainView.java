package com.olskrain.aggregatornews.mvp.view;

/**
 * Created by Andrey Ievlev on 22,Апрель,2019
 */

public interface MainView {
    void addNewChannel();

    void showLoading();

    void hideLoading();

    void showText(String text);
}
