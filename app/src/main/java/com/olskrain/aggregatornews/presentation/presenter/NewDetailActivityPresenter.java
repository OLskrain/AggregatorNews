package com.olskrain.aggregatornews.presentation.presenter;

import com.olskrain.aggregatornews.domain.usecase.NewDetailUseCase;
import com.olskrain.aggregatornews.presentation.ui.view.INewDetailActivityView;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Andrey Ievlev on 11,Май,2019
 */

public class NewDetailActivityPresenter {

    private final Scheduler mainThreadScheduler;
    private final INewDetailActivityView newDetailActivityView;
    private NewDetailUseCase newDetailUseCase;
    private final CompositeDisposable compositeDisposable;
    private Disposable disposable;

    public NewDetailActivityPresenter(final INewDetailActivityView view, final CompositeDisposable compositeDisposable, final Scheduler mainThreadScheduler) {
        this.newDetailActivityView = view;
        this.compositeDisposable = compositeDisposable;
        this.mainThreadScheduler = mainThreadScheduler;
        this.newDetailUseCase = new NewDetailUseCase();
    }

    public void getWebPage(final String urlNews) {
        newDetailActivityView.showLoading();

        Single<String> responseRepository = newDetailUseCase.getWebPage(urlNews);
        disposable = responseRepository
                .observeOn(mainThreadScheduler)
                .subscribe(webPage -> {
                    newDetailActivityView.sendWebPageData(webPage);
                    newDetailActivityView.hideLoading();
                }, throwable -> {
                    newDetailActivityView.hideLoading();
                    newDetailActivityView.showError();
                });

        compositeDisposable.add(disposable);
    }

    public void goToNewsList() {
        newDetailActivityView.goToNewsList();
    }
}

