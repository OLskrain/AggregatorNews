package com.olskrain.aggregatornews.presentation.presenter;

import com.olskrain.aggregatornews.domain.usecase.AddChannelUseCase;
import com.olskrain.aggregatornews.domain.usecase.interfaceUseCase.IAddChannelUseCase;
import com.olskrain.aggregatornews.presentation.ui.view.IAddChannelView;

/**
 * Created by Andrey Ievlev on 25,Май,2019
 */

public class AddChannelPresenter {

    private final IAddChannelView addChannelView;
    private IAddChannelUseCase addChannelUseCase;

    public AddChannelPresenter(IAddChannelView addChannelView) {
        this.addChannelView = addChannelView;
        addChannelUseCase = new AddChannelUseCase();
    }

    public void checkError(final String urlChannel) {
        if(addChannelUseCase.checkError(urlChannel)){
            addChannelView.showError();
        } else {
            addChannelView.hideError();
            addChannelView.goToAllChannelList();
        }
    }
}
