package com.main.pubmanagement.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPreferences {
    private static final String PREF_NAME = "MyPrefs";
    private static MySharedPreferences instance;
    private final SharedPreferences sharedPreferences;

    private MySharedPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized MySharedPreferences getInstance(Context context) {
        if (instance == null) {
            instance = new MySharedPreferences(context);
        }
        return instance;
    }

    public void putString(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public void putInt(String key, int value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public String getString(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    public int getInt(String key, int defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }

}
