package com.olskrain.aggregatornews.presentation.presenter.interfaceRecycleListPresenter;

import com.olskrain.aggregatornews.presentation.ui.view.item.IChannelListItemView;

import io.reactivex.subjects.PublishSubject;

/**
 * Created by Andrey Ievlev on 29,Апрель,2019
 */

public interface IChannelListRVPresenter {

    PublishSubject<IChannelListItemView> getClickOnItem();

    PublishSubject<IChannelListItemView> getClickOnMenu();

    void bindView(IChannelListItemView rowView);

    int getChannelCount();
}
