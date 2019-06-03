package com.olskrain.aggregatornews.presentation.presenter.interfacePresenter;

/**
 * Created by Andrey Ievlev on 04,Июнь,2019
 */

public interface INewsListFragmentPresenter {
    void attachView();
    void refreshNewsList(String urlChannel);
    void saveCurrentUrlChannel();
    void getUrlChannel();
}
