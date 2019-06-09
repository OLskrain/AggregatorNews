package com.olskrain.aggregatornews.presentation.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.olskrain.aggregatornews.R;
import com.olskrain.aggregatornews.abctractFactory.FactoryProvider;
import com.olskrain.aggregatornews.data.cache.SettingsSharedPref;
import com.olskrain.aggregatornews.presentation.presenter.SettingsPresenter;
import com.olskrain.aggregatornews.presentation.presenter.interfacePresenter.ISettingsPresenter;
import com.olskrain.aggregatornews.presentation.ui.view.ISettingsView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Andrey Ievlev on 06,Июнь,2019
 */

public class SettingsActivity extends BaseActivity implements ISettingsView {

    private Toolbar toolbar;
    private ActionBar actionBar;
    private SettingsPresenter settingsPresenter;
    private CompositeDisposable compositeDisposable;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        compositeDisposable = new CompositeDisposable();
        settingsPresenter = FactoryProvider.providerPresenterFactory().createSettingsPresenter(this, compositeDisposable, AndroidSchedulers.mainThread());
        initUi();
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
    public void setAppTheme() {
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
    }
}
