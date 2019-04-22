package com.olskrain.aggregatornews.mvp.presenter;

import com.olskrain.aggregatornews.mvp.view.MainView;

/**
 * Created by Andrey Ievlev on 22,Апрель,2019
 */

public class MainPresenter {
    //private Model model;
    private MainView mainView;

    public MainPresenter(MainView view) {
       // this.model = new Model();
        this.mainView = view;
    }

    public void addNewChannel(){
        mainView.addNewChannel();
    }
}
