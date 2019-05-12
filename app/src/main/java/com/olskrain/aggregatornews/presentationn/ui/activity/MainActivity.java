package com.olskrain.aggregatornews.presentationn.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.olskrain.aggregatornews.R;
import com.olskrain.aggregatornews.presentationn.presenter.MainActivityPresenter;
import com.olskrain.aggregatornews.presentationn.ui.fragment.ChannelsListFragment;
import com.olskrain.aggregatornews.presentationn.ui.fragment.FavoriteChannelsListFragment;
import com.olskrain.aggregatornews.presentationn.ui.fragment.OtherFragment;
import com.olskrain.aggregatornews.presentationn.ui.view.IMainView;

/**
 * Created by Andrey Ievlev on 22,Апрель,2019
 */

public class MainActivity extends AppCompatActivity implements IMainView {
    private static final String ALL_CHANNEL_FRAGMENT_TAG = "43ddDcdd-c9e0-4794-B7e6-cf05af49fbf0";
    private static final String FAVORITE_CHANNEL_FRAGMENT_TAG = "43dbDcdd-c5e0-4794-B7e6-cb05af49fbf0";
    private static final String OTHER_FRAGMENT_TAG = "43ddDcdd-c9e0-i794-B3e6-cf05wf49fbf0";
    private static final int PERMISSION_REQUEST_CODE = 156;
    private MainActivityPresenter mainPresenter;
    private BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainPresenter = new MainActivityPresenter(this);
        initUi();

//        if (ContextCompat.checkSelfPermission(App.getInstance(), Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED
//                || ActivityCompat.checkSelfPermission(App.getInstance(), Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED) {
//            requestPermission(savedInstanceState);
//        } else {
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, ChannelsListFragment.getInstance(ChannelsListFragment.ARG_ACLF_ID), ALL_CHANNEL_FRAGMENT_TAG)
                    .commit();
        }
        // }
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
                addFragment(ChannelsListFragment.getInstance(ChannelsListFragment.ARG_ACLF_ID), ALL_CHANNEL_FRAGMENT_TAG);
                break;
            case R.id.navigation_favorite:
                addFragment(FavoriteChannelsListFragment.getInstance(FavoriteChannelsListFragment.ARG_FCLF_ID), FAVORITE_CHANNEL_FRAGMENT_TAG);
                break;
            case R.id.navigation_other:
                addFragment(OtherFragment.getInstance(OtherFragment.ARG_OF_ID), OTHER_FRAGMENT_TAG);
                break;
        }
    }

    public void addFragment(Fragment fragment, String tag) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment, tag)
                .addToBackStack("")
                .commit();
    }

//    private void requestPermission(Bundle savedInstanceState) {
//        if (ContextCompat.checkSelfPermission(this,
//                Manifest.permission.READ_CONTACTS)
//                != PackageManager.PERMISSION_GRANTED) {
//
//            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
//                    Manifest.permission.READ_CONTACTS)) {
//
//            } else {
//                ActivityCompat.requestPermissions(this,
//                    new String[]{
//                           // Manifest.permission.INTERNET,
//                            Manifest.permission.ACCESS_NETWORK_STATE
//                    },
//                    PERMISSION_REQUEST_CODE);
//            }
//        } else {
//            if (savedInstanceState == null) {
//                getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.container, ChannelsListFragment.getInstance(ChannelsListFragment.ARG_ACLF_ID), ALL_CHANNEL_FRAGMENT_TAG)
//                        .commit();
//            }
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        switch (requestCode) {
//            case PERMISSION_REQUEST_CODE: {
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//                        getSupportFragmentManager()
//                                .beginTransaction()
//                                .replace(R.id.container, ChannelsListFragment.getInstance(ChannelsListFragment.ARG_ACLF_ID), ALL_CHANNEL_FRAGMENT_TAG)
//                                .commit();
//
//                } else {
//                    Timber.d("rty ОТключить функциональность если нет разрешения");
//                }
//                return;
//            }
//        }
//    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
//        if (App.getResponseServiceBroadcast()!= null) {
////            unregisterReceiver(App.getResponseServiceBroadcast());
////        }
////       // unregisterReceiver(App.getResponseServiceBroadcast());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
