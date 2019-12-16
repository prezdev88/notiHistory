package org.prezdev.notihistory.configuration.preferences;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;

import org.prezdev.notihistory.R;
import org.prezdev.notihistory.configuration.preferences.listeners.PreferenceChangeListener;

public class MainPreferenceFragment extends PreferenceFragment {
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        SwitchPreference fragmentTransition = (SwitchPreference)findPreference("fragmentTransition");
        SwitchPreference appItemListAnimation = (SwitchPreference)findPreference("appItemListAnimation");
        SwitchPreference showSystemApps = (SwitchPreference)findPreference("showSystemApps");

        // Listeners
        fragmentTransition.setOnPreferenceChangeListener(new PreferenceChangeListener());
        appItemListAnimation.setOnPreferenceChangeListener(new PreferenceChangeListener());
        showSystemApps.setOnPreferenceChangeListener(new PreferenceChangeListener());

        // en un futuro, cargar los datos de la bd
        fragmentTransition.setChecked(true);
        appItemListAnimation.setChecked(false);
        showSystemApps.setChecked(true);
    }
}