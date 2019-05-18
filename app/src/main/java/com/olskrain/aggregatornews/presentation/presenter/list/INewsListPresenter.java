package com.olskrain.aggregatornews.presentation.presenter.list;

import com.olskrain.aggregatornews.presentation.ui.view.item.INewsListItemView;

/**
 * Created by Andrey Ievlev on 29,Апрель,2019
 */

public interface INewsListPresenter {

    void bindView(INewsListItemView rowView);

    int getNewCount();
}
