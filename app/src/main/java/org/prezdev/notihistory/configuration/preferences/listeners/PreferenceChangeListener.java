package org.prezdev.notihistory.configuration.preferences.listeners;

import android.preference.Preference;

import org.prezdev.notihistory.configuration.Preferences;

public class PreferenceChangeListener implements Preference.OnPreferenceChangeListener {

    private Preferences preferences;

    public PreferenceChangeListener(){
        this.preferences = new Preferences();
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        String key = preference.getKey();
        preferences.save(key, newValue);

        return true;
    }
}
