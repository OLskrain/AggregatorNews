package com.olskrain.aggregatornews.presentation.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.olskrain.aggregatornews.R;
import com.olskrain.aggregatornews.data.cache.SettingsSharedPref;

/**
 * Created by Andrey Ievlev on 08,Июнь,2019
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        applyTheme();
    }

    private void applyTheme() {
        setTheme(getThemeFromPreference());
    }

    public int getThemeFromPreference() {
        boolean blackThemeEnabled = SettingsSharedPref.getInstance().getBlackTheme();
        boolean purpleThemeEnabled = SettingsSharedPref.getInstance().getPurpleTheme();

        if (blackThemeEnabled) {
            return R.style.MyTheme_Black;
        } else if (purpleThemeEnabled) {
            return R.style.MyTheme_Purple;
        } else {
            return R.style.MyTheme;
        }
    }
}
