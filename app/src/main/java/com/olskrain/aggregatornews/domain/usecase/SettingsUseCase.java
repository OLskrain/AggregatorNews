package com.olskrain.aggregatornews.domain.usecase;

import android.content.Context;

import com.olskrain.aggregatornews.Common.LocaleHelper;
import com.olskrain.aggregatornews.R;
import com.olskrain.aggregatornews.abctractFactory.FactoryProvider;
import com.olskrain.aggregatornews.data.repository.interfaceRepository.ISettingsRepository;
import com.olskrain.aggregatornews.domain.usecase.interfaceUseCase.ISettingsUseCase;

import io.reactivex.Completable;

/**
 * Created by Andrey Ievlev on 08,Июнь,2019
 */

public class SettingsUseCase implements ISettingsUseCase {

    private final ISettingsRepository settingsRepository = FactoryProvider.providerRepositoryFactory().createSettingsRepository();

    @Override
    public Completable saveAppTheme(final int idRadioButton) {
        boolean defaultThemeStatus = false, blackThemeStatus = false, purpleThemeStatus = false;
        switch (idRadioButton) {
            case R.id.default_theme_status:
                defaultThemeStatus = true;
                blackThemeStatus = false;
                purpleThemeStatus = false;
                break;
            case R.id.black_theme_status:
                defaultThemeStatus = false;
                blackThemeStatus = true;
                purpleThemeStatus = false;
                break;
            case R.id.purple_theme_status:
                defaultThemeStatus = false;
                blackThemeStatus = false;
                purpleThemeStatus = true;
                break;
        }
        return settingsRepository.saveAppTheme(idRadioButton, defaultThemeStatus, blackThemeStatus, purpleThemeStatus);
    }

    @Override
    public Completable setLanguage(final Context context, final String language) {
        return Completable.fromAction(() -> {
            LocaleHelper.setLocale(context, language);
        });
    }

    @Override
    public void saveIndexRadioButton(final int indexRadioButton) {
        settingsRepository.saveIndexRadioButton(indexRadioButton);
    }
}
