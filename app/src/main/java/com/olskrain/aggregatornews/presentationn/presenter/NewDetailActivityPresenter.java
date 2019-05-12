package com.olskrain.aggregatornews.presentationn.presenter;

import com.olskrain.aggregatornews.domain.usecase.NewDetailUseCase;
import com.olskrain.aggregatornews.presentationn.ui.view.INewDetailActivityView;

/**
 * Created by Andrey Ievlev on 11,Май,2019
 */

public class NewDetailActivityPresenter implements NewDetailUseCase.IResponseServerCallback {

    private INewDetailActivityView newDetailActivityView;
    private NewDetailUseCase newDetailUseCase;

    public NewDetailActivityPresenter(INewDetailActivityView view, String urlNew) {
        this.newDetailActivityView = view;
        this.newDetailUseCase = new NewDetailUseCase(urlNew);
        this.newDetailUseCase.registerCallBack(this);
    }

    public void getWebPage(){
        newDetailActivityView.showLoading();
        newDetailUseCase.getWebPage();
    }

    public void goToNewsList() {
        newDetailActivityView.goToNewsList();
    }

    @Override
    public void onMessageStatus(String message) {

    }

    @Override
    public void onWebPage(String webPage) {
        newDetailActivityView.hideLoading();
        newDetailActivityView.sendWebPageData(webPage);
    }
}

