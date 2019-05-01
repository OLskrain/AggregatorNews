package com.olskrain.aggregatornews.presentationn.presenter.list;

import com.olskrain.aggregatornews.presentationn.ui.view.item.IChannelListItemView;

/**
 * Created by Andrey Ievlev on 29,Апрель,2019
 */

public interface IChannelListPresenter {

    void bindView(IChannelListItemView rowView);

    int getChannelCount();
}
