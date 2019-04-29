package com.olskrain.aggregatornews.mvp.presenter.List;

import com.olskrain.aggregatornews.mvp.view.item.IChannelListItemView;

/**
 * Created by Andrey Ievlev on 29,Апрель,2019
 */

public interface IChannelListPresenter {

    void bindView(IChannelListItemView rowView);

    int getChannelCount();
}
