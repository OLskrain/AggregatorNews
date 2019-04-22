package com.olskrain.aggregatornews.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.olskrain.aggregatornews.App;
import com.olskrain.aggregatornews.R;
import com.olskrain.aggregatornews.mvp.presenter.MainPresenter;
import com.olskrain.aggregatornews.mvp.view.MainView;

import timber.log.Timber;

/**
 * Created by Andrey Ievlev on 22,Апрель,2019
 */

public class MainActivity extends AppCompatActivity implements MainView {

    MainPresenter mainPresenter;
    Toolbar mainToolbar;
    FloatingActionButton addNewChannel;

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

        mainPresenter = new MainPresenter(this);

        addNewChannel = findViewById(R.id.add_new_channel_fab);
    }

    private void initOnClick() {
        addNewChannel.setOnClickListener(view -> {
            mainPresenter.addNewChannel();
        });
    }

    @Override
    public void addNewChannel() {
        Timber.d("rty добавление канала");
    }
}
