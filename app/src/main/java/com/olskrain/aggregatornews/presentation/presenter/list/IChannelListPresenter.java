package com.olskrain.aggregatornews.presentation.presenter.list;

import com.olskrain.aggregatornews.presentation.ui.view.item.IChannelListItemView;

import io.reactivex.subjects.PublishSubject;

/**
 * Created by Andrey Ievlev on 29,Апрель,2019
 */

public interface IChannelListPresenter {

    PublishSubject<IChannelListItemView> getClickOnItem();

    PublishSubject<IChannelListItemView> getClickOnMenu();

    void bindView(IChannelListItemView rowView);

    int getChannelCount();
}
