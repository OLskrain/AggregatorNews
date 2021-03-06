package com.olskrain.aggregatornews.presentation.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.olskrain.aggregatornews.R;
import com.olskrain.aggregatornews.abctractFactory.FactoryProvider;
import com.olskrain.aggregatornews.presentation.presenter.NewsDetailActivityPresenter;
import com.olskrain.aggregatornews.presentation.ui.view.INewsDetailActivityView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Andrey Ievlev on 11,Май,2019
 */

@SuppressLint("Registered")
public class NewsDetailActivity extends BaseActivity implements INewsDetailActivityView {

    private static final String EXTRA_URL_NEW_KEY = "urlNews";
    private Toolbar toolbar;
    private ActionBar actionBar;
    private WebView webView;
    private ProgressBar loadingProgressBar;
    private String urlNew;
    private RelativeLayout webContainer;
    private NewsDetailActivityPresenter newsDetailActivityPresenter;
    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_detail_web_activity);

        compositeDisposable = new CompositeDisposable();
        initUi();

        if (Intent.ACTION_VIEW.equals(getIntent().getAction()) && getIntent().getDataString() != null) {
            urlNew = getIntent().getDataString();
        } else urlNew = getIntent().getStringExtra(EXTRA_URL_NEW_KEY);

        newsDetailActivityPresenter = FactoryProvider.providerPresenterFactory().createNewsDetailActivityPresenter(this, compositeDisposable, AndroidSchedulers.mainThread());
        newsDetailActivityPresenter.getWebPage(urlNew);
    }

    private void initUi() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        webContainer = findViewById(R.id.web_container);
        webView = findViewById(R.id.new_detail_web);
        webView.getSettings().setBuiltInZoomControls(true);
        loadingProgressBar = findViewById(R.id.new_detail_loading_progressBar);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            newsDetailActivityPresenter.goToNewsList();
            return true;
        }
        return super.onOptionsItemSelected(item);
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
    public void showError() {
        Snackbar.make(webContainer, R.string.error_connection, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void goToNewsList() {
        navigateUpTo(new Intent(this, NewsListActivity.class));
    }

    @Override
    public void sendWebPageData(String webPage) {
        webView.loadData(webPage, "text/html; charset=utf-8", "utf-8");
    }

    @Override
    public void onResume() {
        super.onResume();
        newsDetailActivityPresenter.onAttachView(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        newsDetailActivityPresenter.onDetachView();
    }

    @Override
    protected void onStop() {
        super.onStop();
        compositeDisposable.clear();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }
}
