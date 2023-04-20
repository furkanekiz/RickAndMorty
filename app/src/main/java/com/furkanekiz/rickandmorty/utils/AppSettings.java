package com.furkanekiz.rickandmorty.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.core.content.ContextCompat;
public class AppSettings {

    private final SharedPreferences mSharedPreferences;
    private final SharedPreferences.Editor editor;
    private final Context mAppContext;

    public AppSettings(Context mAppContext) {
        this.mAppContext = mAppContext;
        Context safeContext = ContextCompat.createDeviceProtectedStorageContext(this.mAppContext);
        if (safeContext == null) {
            safeContext = this.mAppContext;
        }
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(safeContext);
        editor = mSharedPreferences.edit();
    }

    public void saveFirstAPKRun(Boolean value) {
        editor.putBoolean("FirstAPKRun", value);
        editor.commit();
    }

    public Boolean isFirstAPKRun() {
        String key = "FirstAPKRun";
        return mSharedPreferences.getBoolean(key, true);
    }

}
