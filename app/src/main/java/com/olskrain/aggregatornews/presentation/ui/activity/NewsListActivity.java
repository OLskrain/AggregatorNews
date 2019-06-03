package com.olskrain.aggregatornews.presentation.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.olskrain.aggregatornews.R;
import com.olskrain.aggregatornews.presentation.ui.fragment.NewsListFragment;

/**
 * Created by Andrey Ievlev on 10,Май,2019
 */

@SuppressLint("Registered")
public class NewsListActivity extends AppCompatActivity {

    private static final String NEW_FRAGMENT_TAG = "43ddDldd-c9e8-4554-B7e6-cf05jf49dbf0";
    private Toolbar toolbar;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel_detail);

        initUi();

        if (savedInstanceState == null) {
            String urlChannel = getIntent().getStringExtra(NewsListFragment.ARG_CDF_ID);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.channel_detail_container, NewsListFragment.getInstance(NewsListFragment.ARG_CDF_ID, urlChannel), NEW_FRAGMENT_TAG)
                    .commit();
        }
    }

    private void initUi() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
