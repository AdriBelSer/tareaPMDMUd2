package com.yinya.bellidoserranadrianapmdm03.ui;

import android.content.Context;
import android.content.res.Configuration;

import androidx.appcompat.app.AppCompatActivity;

import com.yinya.bellidoserranadrianapmdm03.MyApp;

import java.util.Locale;

public class BaseActivity extends AppCompatActivity {
    /**
     * The name of the SharedPreferences file used to store application preferences.
     */
    protected static final String PREFS_NAME = "AppPreferences";

    /**
     * The key used to store and retrieve the language preference from SharedPreferences.
     */
    protected static final String KEY_LANGUAGE = "language";

    /**
     * Attaches a new base context to the activity with the specified language configuration.
     *
     * @param newBase the base context to attach.
     */
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(applyLanguageContext(newBase));
    }

    /**
     * Applies the language configuration to the given context based on the saved preferences.
     *
     * @param context the context to modify with the desired language setting.
     * @return a new context with the applied language configuration.
     */
    private Context applyLanguageContext(Context context) {
        MyApp app = (MyApp) context.getApplicationContext();
        String language = app.getSavedLanguage();

        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Configuration config = new Configuration();
        config.setLocale(locale);

        return context.createConfigurationContext(config);
    }
}