package com.olskrain.aggregatornews.presentation.presenter;

import com.olskrain.aggregatornews.abctractFactory.FactoryProvider;
import com.olskrain.aggregatornews.domain.usecase.NewsDetailUseCase;
import com.olskrain.aggregatornews.domain.usecase.interfaceUseCase.INewsDetailUseCase;
import com.olskrain.aggregatornews.presentation.presenter.interfacePresenter.INewsDetailActivityPresenter;
import com.olskrain.aggregatornews.presentation.ui.view.INewDetailActivityView;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

/**
 * Created by Andrey Ievlev on 11,Май,2019
 */

public class NewsDetailActivityPresenter implements INewsDetailActivityPresenter {

    private final Scheduler mainThreadScheduler;
    private final INewDetailActivityView newDetailActivityView;
    private final INewsDetailUseCase newsDetailUseCase = FactoryProvider.providerUseCaseFactory().createNewsDetailUseCase();
    private final CompositeDisposable compositeDisposable;
    private Disposable disposable;

    public NewsDetailActivityPresenter(final INewDetailActivityView view, final CompositeDisposable compositeDisposable, final Scheduler mainThreadScheduler) {
        this.newDetailActivityView = view;
        this.compositeDisposable = compositeDisposable;
        this.mainThreadScheduler = mainThreadScheduler;
    }

    @Override
    public void getWebPage(final String urlNews) {
        newDetailActivityView.showLoading();

        Single<String> responseRepository = newsDetailUseCase.getWebPage(urlNews);
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

    @Override
    public void goToNewsList() {
        newDetailActivityView.goToNewsList();
    }
}

