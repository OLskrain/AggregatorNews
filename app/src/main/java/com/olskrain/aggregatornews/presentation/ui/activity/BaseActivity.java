package com.olskrain.aggregatornews.presentation.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.olskrain.aggregatornews.abctractFactory.FactoryProvider;
import com.olskrain.aggregatornews.presentation.presenter.BasePresenter;
import com.olskrain.aggregatornews.presentation.presenter.interfacePresenter.IBasePresenter;
import com.olskrain.aggregatornews.presentation.ui.view.IBaseView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Andrey Ievlev on 08,Июнь,2019
 */

public class BaseActivity extends AppCompatActivity implements IBaseView {

    private CompositeDisposable compositeDisposable;
    private BasePresenter basePresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        compositeDisposable = new CompositeDisposable();
        basePresenter = FactoryProvider.providerPresenterFactory().createBasePresenter(this, compositeDisposable, AndroidSchedulers.mainThread());
        basePresenter.getAppTheme();
    }

    @Override
    public void applyTheme(final int themeId) {
        setTheme(themeId);
    }
}
