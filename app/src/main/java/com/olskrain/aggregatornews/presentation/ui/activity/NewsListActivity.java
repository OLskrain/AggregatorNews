package com.olskrain.aggregatornews.presentation.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.olskrain.aggregatornews.R;
import com.olskrain.aggregatornews.presentation.ui.fragment.NewsListFragment;

import timber.log.Timber;

/**
 * Created by Andrey Ievlev on 10,Май,2019
 */

@SuppressLint("Registered")
public class NewsListActivity extends BaseActivity {

    private static final String NEW_FRAGMENT_TAG = "43ddDldd-c9e8-4554-B7e6-cf05jf49dbf0";
    private Toolbar toolbar;
    private ActionBar actionBar;
    private CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_list_activity);

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
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        collapsingToolbarLayout = findViewById(R.id.toolbar_layout);
        AppBarLayout appBarLayout = findViewById(R.id.app_bar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle(getResources().getString(R.string.news_list_name));
                    collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.colorBlack));

                    isShow = true;
                } else if(isShow) {
                    collapsingToolbarLayout.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }
}
