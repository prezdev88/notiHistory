package org.prezdev.notihistory.configuration.preferences;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;

import org.prezdev.notihistory.BuildConfig;
import org.prezdev.notihistory.R;
import org.prezdev.notihistory.configuration.Preferences;
import org.prezdev.notihistory.configuration.preferences.listeners.PreferenceChangeListener;
import org.prezdev.notihistory.model.Util;

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

        Preference databaseSize = findPreference(getString(R.string.database_size_key));
        databaseSize.setSummary(Util.getDatabaseSize());

        SwitchPreference fragmentTransition = (SwitchPreference)
                findPreference(getString(R.string.fragment_transition_key));
        SwitchPreference appItemListAnimation = (SwitchPreference)
                findPreference(getString(R.string.app_item_list_animation_key));
        SwitchPreference showSystemApps = (SwitchPreference)
                findPreference(getString(R.string.show_system_apps_key));

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