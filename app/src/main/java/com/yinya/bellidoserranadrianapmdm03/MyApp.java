package com.yinya.bellidoserranadrianapmdm03;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.util.Log;

import java.util.Locale;

public class MyApp extends Application {
    protected static final String PREFS_NAME = "AppPreferences";
    protected static final String KEY_LANGUAGE = "language";
    protected static final String KEY_DELETE = "delete";

    @Override
    public void onCreate() {
        super.onCreate();

        String savedLanguage = getSavedLanguage();
        Log.d("language", savedLanguage);
        applyLanguage(savedLanguage);
    }

    public void applyLanguage(String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Configuration config = new Configuration();
        config.setLocale(locale);

        Context context = getBaseContext().createConfigurationContext(config);
        getBaseContext().getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
    }

    private String getSavedLanguage() {
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return preferences.getString(KEY_LANGUAGE, "en");
    }

    public void saveLanguage(String language) {
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_LANGUAGE, language);
        editor.apply();
    }

    public String getDeletePokemonOption(){
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return preferences.getString(KEY_DELETE, "false");
    }

    public void saveDeletePokemonOption (String deleteOption){
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_DELETE, deleteOption);
        editor.apply();
    }




}
