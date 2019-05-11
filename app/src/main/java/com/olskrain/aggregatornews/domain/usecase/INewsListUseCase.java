package com.olskrain.aggregatornews.domain.usecase;

/**
 * Created by Andrey Ievlev on 11,Май,2019
 */
public interface INewsListUseCase {
    void refreshNewsList();

    void registerCallBack(NewsListUseCase.IResponseDBCallback callback);
}
