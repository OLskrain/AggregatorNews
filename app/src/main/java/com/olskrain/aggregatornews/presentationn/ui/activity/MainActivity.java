package com.olskrain.aggregatornews.presentationn.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.olskrain.aggregatornews.Common.App;
import com.olskrain.aggregatornews.R;
import com.olskrain.aggregatornews.presentationn.presenter.MainPresenter;
import com.olskrain.aggregatornews.presentationn.ui.fragment.AllChannelsListFragment;
import com.olskrain.aggregatornews.presentationn.ui.fragment.FavoriteChannelsListFragment;
import com.olskrain.aggregatornews.presentationn.ui.fragment.OtherFragment;
import com.olskrain.aggregatornews.presentationn.ui.view.IMainView;

/**
 * Created by Andrey Ievlev on 22,Апрель,2019
 */

public class MainActivity extends AppCompatActivity implements IMainView {
    private static final String FRAGMENT_TAG = "43ddDcdd-c9e0-4794-B7e6-cf05af49fbf0";

    private MainPresenter mainPresenter;
    private BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainPresenter = new MainPresenter(this);
        initUi();

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, new AllChannelsListFragment(), FRAGMENT_TAG)
                    .commit();
        }
    }

    private void initUi() {
        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            if (item.getItemId() == R.id.navigation_home ||
                    item.getItemId() == R.id.navigation_favorite ||
                    item.getItemId() == R.id.navigation_other) {
                mainPresenter.goToFragment(item.getItemId());
                return true;
            }
            return false;
        }
    };

    @Override
    public void goToFragment(int buttonID) {
        switch (buttonID) {
            case R.id.navigation_home:
                addFragment(new AllChannelsListFragment());
                break;
            case R.id.navigation_favorite:
                addFragment(new FavoriteChannelsListFragment());
                break;
            case R.id.navigation_other:
                addFragment(new OtherFragment());
                break;
        }
    }

    public void addFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack("")
                .commit();
    }

    @Override
    protected void onPause() {
        super.onPause();
        App.getDbHelper().close();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //unregisterReceiver(App.getResponseServiceBroadcast());
    }
}
