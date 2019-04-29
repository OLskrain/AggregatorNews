package com.olskrain.aggregatornews.mvp.presenter;

import com.olskrain.aggregatornews.mvp.model.repository.RssDataRepo;
import com.olskrain.aggregatornews.mvp.view.MainView;

import timber.log.Timber;

/**
 * Created by Andrey Ievlev on 22,Апрель,2019
 */

public class MainPresenter {
    //private Model model;
    private MainView mainView;
    private RssDataRepo rssDataRepo;

    public MainPresenter(MainView view) {
        // this.model = new Model();
        this.mainView = view;
        this.rssDataRepo = new RssDataRepo();
    }

//    public void addNewChannel(){
//        mainView.addNewChannel();
//    }

    public void loadInfo() {
        mainView.showLoading();
        rssDataRepo.getRssData();
        Timber.d("rty " + rssDataRepo.getRssData());
        //mainView.showText();
        mainView.hideLoading();

    }
}
