package com.olskrain.aggregatornews.presentationn.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.olskrain.aggregatornews.R;
import com.olskrain.aggregatornews.presentationn.presenter.NewDetailActivityPresenter;
import com.olskrain.aggregatornews.presentationn.ui.view.INewDetailActivityView;

/**
 * Created by Andrey Ievlev on 11,Май,2019
 */

@SuppressLint("Registered")
public class NewDetailActivity extends AppCompatActivity implements INewDetailActivityView {

    private static final String EXTRA_URL_NEW_KEY = "url new";
    private Toolbar toolbar;
    private ActionBar actionBar;
    private WebView webView;
    private ProgressBar loadingProgressBar;
    private NewDetailActivityPresenter newDetailActivityPresenter;
    private String urlNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_detail_web);

        initUi();

        urlNew = getIntent().getStringExtra(EXTRA_URL_NEW_KEY);
        newDetailActivityPresenter = new NewDetailActivityPresenter(this, urlNew);
        newDetailActivityPresenter.getWebPage();
    }

    private void initUi() {
        toolbar = findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        webView = findViewById(R.id.new_detail_web);
        webView.getSettings().setBuiltInZoomControls(true);
        loadingProgressBar = findViewById(R.id.new_detail_loading_progressBar);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            newDetailActivityPresenter.goToNewsList();
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
    public void goToNewsList() {
        navigateUpTo(new Intent(this, ChannelDetailActivity.class));
    }

    @Override
    public void sendWebPageData(String webPage) {
        webView.loadData(webPage, "text/html; charset=utf-8", "utf-8");
    }
}
