package com.olskrain.aggregatornews.presentation.presenter.interfacePresenter;

import com.olskrain.aggregatornews.domain.entities.ItemNew;

import java.util.List;

/**
 * Created by Andrey Ievlev on 04,Июнь,2019
 */

public interface INewsListFragmentPresenter {
    void attachView();
    void refreshNewsList(String urlChannel);
    void saveCurrentUrlChannel();
    void getUrlChannel();
    void getRandomNews(List<ItemNew> newsList);
}
