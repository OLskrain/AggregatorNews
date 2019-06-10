package com.olskrain.aggregatornews.presentation.presenter;

import android.content.Context;

import com.olskrain.aggregatornews.Common.Command;
import com.olskrain.aggregatornews.abctractFactory.FactoryProvider;
import com.olskrain.aggregatornews.domain.usecase.interfaceUseCase.ISettingsUseCase;
import com.olskrain.aggregatornews.presentation.presenter.interfacePresenter.ISettingsPresenter;
import com.olskrain.aggregatornews.presentation.presenter.presenterNullCheck.SettingsPresenterNullCheck;
import com.olskrain.aggregatornews.presentation.ui.view.ISettingsView;

import io.reactivex.Completable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Andrey Ievlev on 06,Июнь,2019
 */

public class SettingsPresenter extends SettingsPresenterNullCheck implements ISettingsPresenter {

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
                    getView().restartActivity();
                }, throwable -> {

                });

        compositeDisposable.add(disposable);

    }

    @Override
    public void saveIndexRadioButton(final int indexRadioButton) {
        settingsUseCase.saveIndexRadioButton(indexRadioButton);
    }

    @Override
    public void setLanguage(final Context context, final String language) {
        Completable responseRepository = settingsUseCase.setLanguage(context, language);

        disposable = responseRepository
                .observeOn(mainThreadScheduler)
                .subscribe(() -> {
                    getView().restartActivity();
                }, throwable -> {

                });

        compositeDisposable.add(disposable);
    }

    @Override
    public void showDialog(final Command command) {
        switch (command) {
            case DELETE_ALL_CHANNELS:
                getView().showDialog(command);
                break;
            case LANGUAGE_SELECTION:
                getView().showDialog(command);
                break;
            default:
                break;
        }
    }
}
