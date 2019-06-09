package com.olskrain.aggregatornews.presentation.presenter;

import timber.log.Timber;

/**
 * Created by Andrey Ievlev on 09,Июнь,2019
 */

public abstract class DefaultPresenter<T> {
    private T view;
    private T fakeView;

    public DefaultPresenter() {
        fakeView = createFakeView();
    }

    public abstract T createFakeView();

    public void onAttachView(final T view){
        this.view = view;
    }
    public void onDetachView(){
        this.view = null;
    }

    public T getView(){
        if(view == null){
            Timber.d("getView: view is null, returning fake view.");
            return fakeView;
        } else {
            return view;
        }
    }
}
