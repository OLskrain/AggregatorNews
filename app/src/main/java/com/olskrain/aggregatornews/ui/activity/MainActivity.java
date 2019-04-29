package com.olskrain.aggregatornews.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.olskrain.aggregatornews.R;
import com.olskrain.aggregatornews.mvp.presenter.MainPresenter;
import com.olskrain.aggregatornews.mvp.view.MainView;
import com.olskrain.aggregatornews.ui.adapter.ChannelListRVAdapter;

import timber.log.Timber;

/**
 * Created by Andrey Ievlev on 22,Апрель,2019
 */

public class MainActivity extends AppCompatActivity implements MainView {

    private Toolbar mainToolbar;
    private ProgressBar loadingProgressBar;
    private FloatingActionButton addNewChannel;
    private RecyclerView channelListRecyclerView;

    private ChannelListRVAdapter channelListRVAdapter;
    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainPresenter = new MainPresenter(this);
        initUi();
        initOnClick();
    }

    private void initUi() {
        mainToolbar = findViewById(R.id.am_toolbar);
        setSupportActionBar(mainToolbar);
        mainToolbar.setTitle(getTitle());

        //Todo: разобраться с ра=ботой прогресбара или заменить
        loadingProgressBar = findViewById(R.id.loadingProgressBar);
        hideLoading();
        addNewChannel = findViewById(R.id.add_new_channel_fab);

        channelListRecyclerView = findViewById(R.id.channel_list);
        channelListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        channelListRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        channelListRVAdapter = new ChannelListRVAdapter(mainPresenter.channelListPresenter);
        channelListRecyclerView.setAdapter(channelListRVAdapter);
    }


    private void initOnClick() {
        //Todo: потом сменить назначение кнопки, а пока проверяем работу сервера
        addNewChannel.setOnClickListener(view -> {

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
        loadingProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showText(String text) {

    }

    @Override
    protected void onDestroy() { //отписываемся тут, чтобы данные обновились если пользователь свернул прилодение
        super.onDestroy();
        //unregisterReceiver(myBroadcastReceiver);
    }
}
