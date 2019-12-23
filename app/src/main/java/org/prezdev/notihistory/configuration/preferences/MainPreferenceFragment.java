package org.prezdev.notihistory.configuration.preferences;

import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;

import org.prezdev.notihistory.BuildConfig;
import org.prezdev.notihistory.R;
import org.prezdev.notihistory.configuration.Preferences;
import org.prezdev.notihistory.configuration.preferences.listeners.PreferenceChangeListener;
import org.prezdev.notihistory.model.Util;

public class MainPreferenceFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener {

    private Preferences preferences;
    private EditTextPreference transitionDuration;
    private EditTextPreference animationDuration;

    private SwitchPreference fragmentTransition;
    private SwitchPreference appItemListAnimation;
    private SwitchPreference showSystemApps;

    public MainPreferenceFragment(){
        this.preferences = new Preferences();
    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        // Load components
        Preference version = findPreference(getString(R.string.version));
        version.setSummary("v"+BuildConfig.VERSION_NAME);

        Preference databaseSize = findPreference(getString(R.string.database_size_key));
        databaseSize.setSummary(Util.getDatabaseSize());

        fragmentTransition = (SwitchPreference)
                findPreference(getString(R.string.fragment_transition_key));

        appItemListAnimation = (SwitchPreference)
                findPreference(getString(R.string.app_item_list_animation_key));

        showSystemApps = (SwitchPreference)
                findPreference(getString(R.string.show_system_apps_key));

        transitionDuration = (EditTextPreference)
                findPreference(getString(R.string.transition_duration_key));
        
        animationDuration = (EditTextPreference)
                findPreference(getString(R.string.animation_duration_key));

        // Listeners
        fragmentTransition.setOnPreferenceChangeListener(new PreferenceChangeListener(this));
        appItemListAnimation.setOnPreferenceChangeListener(new PreferenceChangeListener(this));
        showSystemApps.setOnPreferenceChangeListener(new PreferenceChangeListener());
        transitionDuration.setOnPreferenceChangeListener(new PreferenceChangeListener(this));
        animationDuration.setOnPreferenceChangeListener(new PreferenceChangeListener(this));

        // loadDatabase data
        fragmentTransition.setChecked(preferences.isFragmentTransition());
        appItemListAnimation.setChecked(preferences.isAppItemListAnimation());
        showSystemApps.setChecked(preferences.isShowSystemApps());
        transitionDuration.setSummary(preferences.getTransitionDuration()+" ms");
        animationDuration.setSummary(preferences.getAnimationDuration()+" ms");

        transitionDuration.setEnabled(fragmentTransition.isChecked());
        animationDuration.setEnabled(appItemListAnimation.isChecked());
    }

    /**
     * Esto lo hice porque cuando cambia un preference y necesito cambiar algún componente
     * gráfico, llamo a este método desde la clase PreferenceChangeListener.java
     * @param preference
     * @param newValue
     * @return
     */
    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        String key = preference.getKey();

        if(key.equals(getString(R.string.transition_duration_key))){
            transitionDuration.setSummary(newValue+" ms");
        }else if(key.equals(getString(R.string.animation_duration_key))){
            animationDuration.setSummary(newValue+" ms");
        }else if(key.equals(getString(R.string.fragment_transition_key))){
            transitionDuration.setEnabled(!fragmentTransition.isChecked());
        }else if(key.equals(getString(R.string.app_item_list_animation_key))){
            animationDuration.setEnabled(!appItemListAnimation.isChecked());
        }

        return true;
    }
}