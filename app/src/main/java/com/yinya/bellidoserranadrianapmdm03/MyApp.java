package com.yinya.bellidoserranadrianapmdm03;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.Log;

import java.util.Locale;

public class MyApp extends Application {
    protected static final String PREFS_NAME = "AppPreferences";
    protected static final String KEY_LANGUAGE = "language";
    protected static final String KEY_DELETE = "delete";

    @Override
    public void onCreate() {
        super.onCreate();

        // Apply the saved language on App initialization
        String savedLanguage = getSavedLanguage();
        applyLanguage(savedLanguage);
    }

    public void applyLanguage(String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Resources resources = getBaseContext().getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        config.setLayoutDirection(locale);

        Context context = getBaseContext();
        context.createConfigurationContext(config);
        context.getResources().updateConfiguration(config, resources.getDisplayMetrics());
    }

    public void applySavedLanguage(Activity activity) {
        String savedLanguage = getSavedLanguage();
        Log.d(KEY_LANGUAGE, savedLanguage);
        applyLanguage(savedLanguage);
        activity.recreate();
    }

    public String getSavedLanguage() {
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
