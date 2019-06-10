package com.olskrain.aggregatornews.presentation.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.olskrain.aggregatornews.Common.App;
import com.olskrain.aggregatornews.Common.Command;
import com.olskrain.aggregatornews.Common.myObserver.IActionAboveApplicationParametersCustomObserver;
import com.olskrain.aggregatornews.Common.myObserver.ICustomPublisher;
import com.olskrain.aggregatornews.R;
import com.olskrain.aggregatornews.abctractFactory.FactoryProvider;
import com.olskrain.aggregatornews.data.cache.SettingsSharedPref;
import com.olskrain.aggregatornews.presentation.presenter.SettingsPresenter;
import com.olskrain.aggregatornews.presentation.ui.fragment.DeleteAllChannelDialog;
import com.olskrain.aggregatornews.presentation.ui.fragment.LanguageSelectionDialog;
import com.olskrain.aggregatornews.presentation.ui.view.ISettingsView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Andrey Ievlev on 06,Июнь,2019
 */

public class SettingsActivity extends BaseActivity implements ISettingsView, IActionAboveApplicationParametersCustomObserver {

    private static final String LANGUAGE_EN = "en";
    private static final String LANGUAGE_RU = "ru";
    private static final String DELETE_ALL_CHANNEL_DIALOG = "deleteAllChannelDialog";
    private static final String LANGUAGE_SELECTION_DIALOG = "languageSelectionDialog";
    private Toolbar toolbar;
    private ActionBar actionBar;
    private SettingsPresenter settingsPresenter;
    private CompositeDisposable compositeDisposable;
    private RadioGroup radioGroup;
    private TextView deleteAllChannels;
    private TextView language;
    private ICustomPublisher.IActionAppParameters publisher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        publisher = App.getInstance().getPublisherActionAppParameters();
        publisher.subscribe(this);

        compositeDisposable = new CompositeDisposable();
        settingsPresenter = FactoryProvider.providerPresenterFactory().createSettingsPresenter(this, compositeDisposable, AndroidSchedulers.mainThread());
        initUi();
        initOnClick();
    }

    private void initUi() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        radioGroup = findViewById(R.id.switch_theme);
        int indexRadioButton = SettingsSharedPref.getInstance().getIndexRadioButton();
        RadioButton savedCheckedRadioButton = (RadioButton) radioGroup.getChildAt(indexRadioButton);
        savedCheckedRadioButton.setChecked(true);

        checkSelectionAppTheme();

        language = findViewById(R.id.language_selection);
        deleteAllChannels = findViewById(R.id.delete_all_channels);
    }

    private void initOnClick() {
        deleteAllChannels.setOnClickListener(view -> {
            settingsPresenter.showDialog(Command.DELETE_ALL_CHANNELS);
        });

        language.setOnClickListener(view -> {
            settingsPresenter.showDialog(Command.LANGUAGE_SELECTION);
        });
    }

    private void checkSelectionAppTheme() {
        radioGroup.setOnCheckedChangeListener((radioGroup, idRadioButton) -> {
            RadioButton checkedRadioButton = radioGroup.findViewById(idRadioButton);
            int indexRadioButton = radioGroup.indexOfChild(checkedRadioButton);

            settingsPresenter.saveAppTheme(idRadioButton);
            settingsPresenter.saveIndexRadioButton(indexRadioButton);
        });
    }

    @Override
    public void restartActivity() {
        new Handler().post(() -> {
            Intent intent = getIntent();
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION);
            overridePendingTransition(0, 0);
            finish();

            overridePendingTransition(0, 0);
            startActivity(intent);
        });
    }

    @Override
    public void showDialog(final Command command) {
        switch (command) {
            case DELETE_ALL_CHANNELS:
                DialogFragment deleteAllChannelDialog = new DeleteAllChannelDialog();
                deleteAllChannelDialog.show(getSupportFragmentManager(), DELETE_ALL_CHANNEL_DIALOG);
                break;
            case LANGUAGE_SELECTION:
                DialogFragment languageSelectionDialog = new LanguageSelectionDialog();
                languageSelectionDialog.show(getSupportFragmentManager(), LANGUAGE_SELECTION_DIALOG);
                break;
            default:
                break;
        }
    }

    @Override
    public void actionAboveApplicationParameters(final Command command) {
        switch (command) {
            case SELECTION_EN:
                settingsPresenter.setLanguage(this, LANGUAGE_EN);
                break;
            case SELECTION_RU:
                settingsPresenter.setLanguage(this, LANGUAGE_RU);
                break;
            default:
                settingsPresenter.setLanguage(this, LANGUAGE_EN);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        settingsPresenter.onAttachView(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        settingsPresenter.onDetachView();
    }

    @Override
    public void onStop() {
        super.onStop();
        compositeDisposable.clear();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
        publisher.unsubscribe(this);
    }
}
