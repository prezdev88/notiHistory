package org.prezdev.notihistory.configuration.preferences;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;

import org.prezdev.notihistory.BuildConfig;
import org.prezdev.notihistory.R;
import org.prezdev.notihistory.configuration.Preferences;
import org.prezdev.notihistory.configuration.preferences.listeners.PreferenceChangeListener;

public class MainPreferenceFragment extends PreferenceFragment {

    private Preferences preferences;

    public MainPreferenceFragment(){
        this.preferences = new Preferences();
    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        Preference version = findPreference(getString(R.string.version));
        version.setSummary("v"+BuildConfig.VERSION_NAME);

        SwitchPreference fragmentTransition = (SwitchPreference)findPreference("fragmentTransition");
        SwitchPreference appItemListAnimation = (SwitchPreference)findPreference("appItemListAnimation");
        SwitchPreference showSystemApps = (SwitchPreference)findPreference("showSystemApps");

        // Listeners
        fragmentTransition.setOnPreferenceChangeListener(new PreferenceChangeListener());
        appItemListAnimation.setOnPreferenceChangeListener(new PreferenceChangeListener());
        showSystemApps.setOnPreferenceChangeListener(new PreferenceChangeListener());

        // en un futuro, cargar los datos de la bd
        fragmentTransition.setChecked(preferences.isFragmentTransition());
        appItemListAnimation.setChecked(preferences.isAppItemListAnimation());
        showSystemApps.setChecked(preferences.isShowSystemApps());
    }
}