package com.olskrain.aggregatornews.presentation.presenter;

import com.olskrain.aggregatornews.abctractFactory.FactoryProvider;
import com.olskrain.aggregatornews.domain.usecase.interfaceUseCase.ISettingsUseCase;
import com.olskrain.aggregatornews.presentation.presenter.interfacePresenter.ISettingsPresenter;
import com.olskrain.aggregatornews.presentation.ui.view.ISettingsView;

import io.reactivex.Completable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Andrey Ievlev on 06,Июнь,2019
 */

public class SettingsPresenter implements ISettingsPresenter {

    private final ISettingsView settingsView;
    private final CompositeDisposable compositeDisposable;
    private final Scheduler mainThreadScheduler;
    private final ISettingsUseCase settingsUseCase = FactoryProvider.providerUseCaseFactory().createSettingsUseCase();
    private Disposable disposable;

    public SettingsPresenter(final ISettingsView view, final CompositeDisposable compositeDisposable, final Scheduler mainThreadScheduler) {
        this.settingsView = view;
        this.compositeDisposable = compositeDisposable;
        this.mainThreadScheduler = mainThreadScheduler;
    }

    @Override
    public void saveAppTheme(final int idRadioButton) {
        Completable responseRepository = settingsUseCase.saveAppTheme(idRadioButton);

        disposable = responseRepository
                .observeOn(mainThreadScheduler)
                .subscribe(() -> {
                    settingsView.setAppTheme();
                }, throwable -> {

                });

        compositeDisposable.add(disposable);

    }

    @Override
    public void saveIndexRadioButton(int indexRadioButton) {
        settingsUseCase.saveIndexRadioButton(indexRadioButton);
    }
}
