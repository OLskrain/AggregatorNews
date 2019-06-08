package com.olskrain.aggregatornews.data.cache;

import android.content.SharedPreferences;

import com.olskrain.aggregatornews.Common.App;

import static android.content.Context.MODE_PRIVATE;

public class SettingsSharedPref {
    private static final String SHARED_PREFERENCES_TAG = "SETTINGS_SP";
    private static final String THEME_DEFAULT_KEY = "DEFAULT_THEME";
    private static final String THEME_BLACK_KEY = "BLACK_THEME";
    private static final String THEME_PURPLE_KEY = "PURPLE_THEME";
    private static final String RADIOBUTTON_INDEX_KEY = "RADIO_BUTTON_INDEX";
    private static volatile SettingsSharedPref instance = null;
    private final SharedPreferences preferences;

    private SettingsSharedPref() {
        preferences = App.getInstance().getSharedPreferences(SHARED_PREFERENCES_TAG, MODE_PRIVATE);
    }

    public static SettingsSharedPref getInstance() {
        SettingsSharedPref localInstance = instance;
        if (localInstance == null) {
            synchronized (SettingsSharedPref.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new SettingsSharedPref();
                }
            }
        }
        return localInstance;
    }

    public boolean getDefaultTheme() {
        return preferences.getBoolean(THEME_DEFAULT_KEY, false);
    }

    public boolean getBlackTheme() {
        return preferences.getBoolean(THEME_BLACK_KEY, false);
    }

    public boolean getPurpleTheme() {
        return preferences.getBoolean(THEME_PURPLE_KEY, false);
    }

    public int getIndexRadioButton() {
        return preferences.getInt(RADIOBUTTON_INDEX_KEY, 0);
    }

    public void putDefaultThemeStatus(final boolean status) {
        preferences.edit().putBoolean(THEME_DEFAULT_KEY, status).apply();
    }

    public void putBlackThemeStatus(final boolean status) {
        preferences.edit().putBoolean(THEME_BLACK_KEY, status).apply();
    }

    public void putPurpleThemeStatus(final boolean status) {
        preferences.edit().putBoolean(THEME_PURPLE_KEY, status).apply();
    }

    public void putIndexRadioButton(final int idRadioButton) {
        preferences.edit().putInt(RADIOBUTTON_INDEX_KEY, idRadioButton).apply();
    }
}
