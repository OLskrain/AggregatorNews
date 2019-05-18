package com.olskrain.aggregatornews.presentation.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.olskrain.aggregatornews.R;
import com.olskrain.aggregatornews.presentation.presenter.ChannelDetailActivityPresenter;
import com.olskrain.aggregatornews.presentation.ui.fragment.ChannelDetailFragment;
import com.olskrain.aggregatornews.presentation.ui.view.IChannelDetailActivityView;

/**
 * Created by Andrey Ievlev on 10,Май,2019
 */

@SuppressLint("Registered")
public class ChannelDetailActivity extends AppCompatActivity implements IChannelDetailActivityView {

    private static final String NEW_FRAGMENT_TAG = "43ddDldd-c9e8-4554-B7e6-cf05jf49dbf0";
    private Toolbar toolbar;
    private ActionBar actionBar;
    private ChannelDetailActivityPresenter channelDetailActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel_detail);

        initUi();

        channelDetailActivityPresenter = new ChannelDetailActivityPresenter(this);

        if (savedInstanceState == null) {
            int channelPosition = getIntent().getIntExtra(ChannelDetailFragment.ARG_CDF_ID, 0);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.channel_detail_container, ChannelDetailFragment.getInstance(ChannelDetailFragment.ARG_CDF_ID, channelPosition), NEW_FRAGMENT_TAG)
                    .commit();
        }
    }

    private void initUi() {
        toolbar = findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            channelDetailActivityPresenter.doToAllChannelsList();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void goToAllChannelsList() {
        navigateUpTo(new Intent(this, MainActivity.class));
    }
}
