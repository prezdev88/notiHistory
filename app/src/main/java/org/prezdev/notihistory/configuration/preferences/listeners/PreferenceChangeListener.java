package org.prezdev.notihistory.configuration.preferences.listeners;

import android.preference.Preference;

import org.prezdev.notihistory.MainActivity;
import org.prezdev.notihistory.R;
import org.prezdev.notihistory.cache.Cache;
import org.prezdev.notihistory.configuration.Preferences;

public class PreferenceChangeListener implements Preference.OnPreferenceChangeListener {

    private Preferences preferences;
    private Preference.OnPreferenceChangeListener onPreferenceChangeListener;

    public PreferenceChangeListener(){
        this.preferences = new Preferences();
    }

    public PreferenceChangeListener(Preference.OnPreferenceChangeListener onPreferenceChangeListener){
        this();
        this.onPreferenceChangeListener = onPreferenceChangeListener;
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        String key = preference.getKey();
        preferences.save(key, newValue);

        if(key.equals(MainActivity.getActivity().getString(R.string.show_system_apps_key))){
            Cache.showSystemAppsSettingsChange = true;
        }

        if(this.onPreferenceChangeListener != null){
            // Con esto le aviso al listener que debe cambiar el valor de forma gráfica
            // En clase MainPreferenceFragment.java
            this.onPreferenceChangeListener.onPreferenceChange(preference, newValue);
        }

        return true;
    }
}
