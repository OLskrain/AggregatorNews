package com.olskrain.aggregatornews.presentation.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.olskrain.aggregatornews.Common.App;
import com.olskrain.aggregatornews.R;
import com.olskrain.aggregatornews.presentation.presenter.MainActivityPresenter;
import com.olskrain.aggregatornews.presentation.ui.adapter.CustomFragmentPA;
import com.olskrain.aggregatornews.presentation.ui.fragment.ChannelsListFragment;
import com.olskrain.aggregatornews.presentation.ui.fragment.FavoriteChannelsListFragment;
import com.olskrain.aggregatornews.presentation.ui.view.IMainView;

import io.reactivex.disposables.CompositeDisposable;
import timber.log.Timber;

/**
 * Created by Andrey Ievlev on 22,Апрель,2019
 */

public class MainActivity extends AppCompatActivity implements IMainView {
    private static final int PERMISSION_REQUEST_CODE = 156;
    private ViewPager mainViewPager;
    private CustomFragmentPA customFragmentPA;
    private TabLayout mainTabLayout;
    private Toolbar toolbar;
    private MainActivityPresenter mainPresenter;
    private ChannelsListFragment channelsListFragment;
    private FavoriteChannelsListFragment favoriteChannelsListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainPresenter = new MainActivityPresenter(this);
        initUI();

//        if (ContextCompat.checkSelfPermission(App.getInstance(), Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED
//                || ActivityCompat.checkSelfPermission(App.getInstance(), Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED) {
//            requestPermission(savedInstanceState);
//        } else {


//        if (savedInstanceState == null) {
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .replace(R.id.container, ChannelsListFragment.getInstance(ChannelsListFragment.ARG_ACLF_ID), ALL_CHANNEL_FRAGMENT_TAG)
//                    .commit();
//        }
        // }
    }

    public void initUI() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        channelsListFragment = ChannelsListFragment.getInstance(ChannelsListFragment.ARG_ACLF_ID);
        favoriteChannelsListFragment = FavoriteChannelsListFragment.getInstance(FavoriteChannelsListFragment.ARG_FCLF_ID);

        customFragmentPA = new CustomFragmentPA(getSupportFragmentManager());
        customFragmentPA.addFragment(channelsListFragment, getString(R.string.channels_list_tab_title));
        customFragmentPA.addFragment(favoriteChannelsListFragment, getString(R.string.favorites_list_tab_title));

        mainViewPager = findViewById(R.id.container);
        mainViewPager.setAdapter(customFragmentPA);
        mainTabLayout = findViewById(R.id.tabs);
        mainTabLayout.setupWithViewPager(mainViewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.settings_menu) {
            mainPresenter.goToFragment();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public void goToFragment(int buttonID) {
//        switch (buttonID) {
//            case R.id.navigation_home:
//                addFragment(ChannelsListFragment.getInstance(ChannelsListFragment.ARG_ACLF_ID), ALL_CHANNEL_FRAGMENT_TAG);
//                break;
//            case R.id.navigation_favorite:
//                addFragment(FavoriteChannelsListFragment.getInstance(FavoriteChannelsListFragment.ARG_FCLF_ID), FAVORITE_CHANNEL_FRAGMENT_TAG);
//                break;
//            case R.id.navigation_other:
//                addFragment(OtherFragment.getInstance(OtherFragment.ARG_OF_ID), OTHER_FRAGMENT_TAG);
//                break;
//        }
//    }

//    public void addFragment(Fragment fragment, String tag) {
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.container, fragment, tag)
//                .addToBackStack("")
//                .commit();
//    }


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
    public void goToSettingsActivity() {
        Timber.d("rty set ");
    }

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
        App.getInstance().getCompositeDisposable().clear();
    }
}
