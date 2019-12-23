package org.prezdev.notihistory.configuration;

import android.content.Context;
import android.content.SharedPreferences;

import org.prezdev.notihistory.MainActivity;
import org.prezdev.notihistory.R;

public class Preferences {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;

    // Shared preferences file name
    private final String PREF_NAME = "notihistory-preferences";

    private String fragmentTransitionKey;
    private String appItemListAnimationKey;
    private String showSystemAppsKey;
    private String firstTimeLaunchKey;
    private String transitionDurationKey;
    private String animationDurationKey;

    public Preferences() {
        this(MainActivity.getActivity());
    }

    public Preferences(Context context){
        this.context = context;

        sharedPreferences = this.context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        loadKeys();
    }

    private void loadKeys() {
        fragmentTransitionKey = context.getString(R.string.fragment_transition_key);
        appItemListAnimationKey = context.getString(R.string.app_item_list_animation_key);
        showSystemAppsKey = context.getString(R.string.show_system_apps_key);
        firstTimeLaunchKey = context.getString(R.string.first_time_launch_key);
        transitionDurationKey = context.getString(R.string.transition_duration_key);
        animationDurationKey = context.getString(R.string.animation_duration_key);
    }

    public void setFragmentTransition(boolean fragmentTransition){
        editor.putBoolean(fragmentTransitionKey, fragmentTransition);
        editor.commit();
    }

    public void setAppItemListAnimation(boolean appItemListAnimation){
        editor.putBoolean(appItemListAnimationKey, appItemListAnimation);
        editor.commit();
    }

    public void setShowSystemApps(boolean showSystemApps){
        editor.putBoolean(showSystemAppsKey, showSystemApps);
        editor.commit();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(firstTimeLaunchKey, isFirstTime);
        editor.commit();
    }

    public boolean isFragmentTransition() {
        return sharedPreferences.getBoolean(fragmentTransitionKey, false);
    }

    public boolean isAppItemListAnimation() {
        return sharedPreferences.getBoolean(appItemListAnimationKey, false);
    }

    public boolean isShowSystemApps() {
        return sharedPreferences.getBoolean(showSystemAppsKey, false);
    }

    public boolean isFirstTimeLaunch() {
        return sharedPreferences.getBoolean(firstTimeLaunchKey, true);
    }

    public long getTransitionDuration(){
        return Long.parseLong(sharedPreferences.getString(transitionDurationKey, "250"));
    }

    public long getAnimationDuration(){
        return Long.parseLong(sharedPreferences.getString(animationDurationKey, "100"));
    }

    public void save(String key, Object value) {
        if(value instanceof Boolean){
            editor.putBoolean(key, Boolean.parseBoolean(value.toString()));
        }else {
            editor.putString(key, value.toString());
        }

        editor.commit();
    }
}
