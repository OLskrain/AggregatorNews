package com.olskrain.aggregatornews.presentation.presenter;

import com.olskrain.aggregatornews.domain.usecase.NewDetailUseCase;
import com.olskrain.aggregatornews.presentation.ui.view.INewDetailActivityView;

/**
 * Created by Andrey Ievlev on 11,Май,2019
 */

public class NewDetailActivityPresenter  {

    private INewDetailActivityView newDetailActivityView;
    private NewDetailUseCase newDetailUseCase;

    public NewDetailActivityPresenter(INewDetailActivityView view, String urlNew) {
        this.newDetailActivityView = view;
        this.newDetailUseCase = new NewDetailUseCase(urlNew);
    }

    public void getWebPage(){
        newDetailActivityView.showLoading();
        newDetailUseCase.getWebPage();
    }

    public void goToNewsList() {
        newDetailActivityView.goToNewsList();
    }

}

