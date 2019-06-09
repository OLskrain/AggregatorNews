package com.olskrain.aggregatornews.presentation.presenter;

import com.olskrain.aggregatornews.abctractFactory.FactoryProvider;
import com.olskrain.aggregatornews.domain.usecase.interfaceUseCase.IAddChannelUseCase;
import com.olskrain.aggregatornews.presentation.presenter.interfacePresenter.IAddChannelPresenter;
import com.olskrain.aggregatornews.presentation.presenter.presenterNullCheck.AddChannelPresenterNullCheck;
import com.olskrain.aggregatornews.presentation.ui.view.IAddChannelView;

/**
 * Created by Andrey Ievlev on 25,Май,2019
 */

public class AddChannelPresenter extends AddChannelPresenterNullCheck implements IAddChannelPresenter {

    private final IAddChannelView addChannelView;
    private final IAddChannelUseCase addChannelUseCase = FactoryProvider.providerUseCaseFactory().createAddChannelUseCase();

    public AddChannelPresenter(final IAddChannelView addChannelView) {
        this.addChannelView = addChannelView;
    }

    @Override
    public void checkError(final String urlChannel) {
        if (addChannelUseCase.checkError(urlChannel)) {
            getView().showError();
        } else {
            getView().hideError();
            getView().goToAllChannelList();
        }
    }
}
