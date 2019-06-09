package com.olskrain.aggregatornews.presentation.presenter;

import com.olskrain.aggregatornews.abctractFactory.FactoryProvider;
import com.olskrain.aggregatornews.domain.usecase.interfaceUseCase.IBaseUseCase;
import com.olskrain.aggregatornews.presentation.presenter.interfacePresenter.IBasePresenter;
import com.olskrain.aggregatornews.presentation.ui.view.IBaseView;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Andrey Ievlev on 09,Июнь,2019
 */

public class BasePresenter implements IBasePresenter {

    private final IBaseView baseView;
    private final CompositeDisposable compositeDisposable;
    private final Scheduler mainThreadScheduler;
    private final IBaseUseCase baseUseCase = FactoryProvider.providerUseCaseFactory().createBaseUseCase();
    private Disposable disposable;

    public BasePresenter(final IBaseView baseView, final CompositeDisposable compositeDisposable, final Scheduler mainThreadScheduler) {
        this.baseView = baseView;
        this.compositeDisposable = compositeDisposable;
        this.mainThreadScheduler = mainThreadScheduler;
    }

    @Override
    public void getAppTheme() {
        Single<Integer> responseRepository = baseUseCase.getAppTheme();

        disposable = responseRepository
                .subscribe(themeId -> {
                    baseView.applyTheme(themeId);
                }, throwable -> {

                });

        compositeDisposable.add(disposable);
    }
}
