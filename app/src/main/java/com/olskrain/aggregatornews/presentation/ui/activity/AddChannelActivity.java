package com.olskrain.aggregatornews.presentation.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.olskrain.aggregatornews.R;
import com.olskrain.aggregatornews.abctractFactory.FactoryProvider;
import com.olskrain.aggregatornews.presentation.presenter.interfacePresenter.IAddChannelPresenter;
import com.olskrain.aggregatornews.presentation.ui.view.IAddChannelView;

/**
 * Created by Andrey Ievlev on 25,Май,2019
 */


public class AddChannelActivity extends BaseActivity implements IAddChannelView {

    public static final String URL_CHANNEL = "urlChannel";
    private static final String EMPTY_STRING = "";
    private Toolbar toolbar;
    private ActionBar actionBar;
    private TextInputLayout textInputEditText;
    private EditText addChannelET;
    private IAddChannelPresenter addChannelPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_channel_activity);

        addChannelPresenter = FactoryProvider.providerPresenterFactory().createAddChannelPresenter(this);

        initUi();
    }

    private void initUi() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        textInputEditText = findViewById(R.id.text_input_layout);
        addChannelET = findViewById(R.id.add_new_channel_et);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_channel_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.send_menu) {
            addChannelPresenter.checkError(addChannelET.getText().toString());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void goToAllChannelList() {
        Intent intent = new Intent();
        intent.putExtra(URL_CHANNEL, addChannelET.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void showError() {
        textInputEditText.setError(getString(R.string.error_invalid_url));
    }

    @Override
    public void hideError() {
        textInputEditText.setError(EMPTY_STRING);
    }
}


