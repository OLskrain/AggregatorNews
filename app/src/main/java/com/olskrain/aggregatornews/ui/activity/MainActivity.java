package com.olskrain.aggregatornews.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.olskrain.aggregatornews.R;
import com.olskrain.aggregatornews.mvp.model.repository.RssDataRepo;
import com.olskrain.aggregatornews.mvp.model.service.DataDownloadService;
import com.olskrain.aggregatornews.mvp.presenter.MainPresenter;
import com.olskrain.aggregatornews.mvp.view.MainView;

import timber.log.Timber;

/**
 * Created by Andrey Ievlev on 22,Апрель,2019
 */

public class MainActivity extends AppCompatActivity implements MainView {

    private Toolbar mainToolbar;
    private ProgressBar loadingProgressBar;
    private FloatingActionButton addNewChannel;
    private TextView probText;

    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUi();
        initOnClick();
    }

    private void initUi() {
        mainToolbar = findViewById(R.id.am_toolbar);
        setSupportActionBar(mainToolbar);
        mainToolbar.setTitle(getTitle());

        loadingProgressBar = findViewById(R.id.loadingProgressBar);
        hideLoading();

        addNewChannel = findViewById(R.id.add_new_channel_fab);
        probText = findViewById(R.id.ProbText);

        mainPresenter = new MainPresenter(this);

    }


    private void initOnClick() {
        //Todo: потом сменить назначение кнопки, а пока проверяем работу сервера
        addNewChannel.setOnClickListener(view -> {
            probText.setText("");
            mainPresenter.loadInfo();
            //mainPresenter.addNewChannel();
        });
    }

    @Override
    public void addNewChannel() {
        Timber.d("rty добавление канала");
    }

    @Override
    public void showLoading() {
        loadingProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loadingProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showText(String text) {
        probText.setText(text);
    }

    @Override
    protected void onDestroy() { //отписываемся тут, чтобы данные обновились если пользователь свернул прилодение
        super.onDestroy();
        //unregisterReceiver(myBroadcastReceiver);
    }
}
