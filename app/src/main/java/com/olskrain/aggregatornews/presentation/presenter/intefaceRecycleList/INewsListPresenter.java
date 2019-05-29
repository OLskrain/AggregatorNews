package com.olskrain.aggregatornews.presentation.presenter.intefaceRecycleList;

import com.olskrain.aggregatornews.presentation.ui.view.item.INewsListItemView;

import io.reactivex.subjects.PublishSubject;

/**
 * Created by Andrey Ievlev on 29,Апрель,2019
 */

public interface INewsListPresenter {

    PublishSubject<INewsListItemView> getClickOnItem();

    void bindView(INewsListItemView rowView);

    int getNewCount();
}
