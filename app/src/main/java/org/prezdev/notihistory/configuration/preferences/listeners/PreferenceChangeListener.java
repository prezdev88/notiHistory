package org.prezdev.notihistory.configuration.preferences.listeners;

import android.preference.Preference;

public class PreferenceChangeListener implements Preference.OnPreferenceChangeListener {
    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        String value = newValue.toString();
        String key = preference.getKey();

        System.out.println(key);
        System.out.println(value);

        return true;
    }
}
