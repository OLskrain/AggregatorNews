package com.olskrain.aggregatornews.presentationn.presenter.list;

import com.olskrain.aggregatornews.presentationn.ui.view.item.INewsListItemView;

/**
 * Created by Andrey Ievlev on 29,Апрель,2019
 */

public interface INewsListPresenter {

    void bindView(INewsListItemView rowView);

    int getNewCount();
}
