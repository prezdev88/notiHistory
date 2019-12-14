package org.prezdev.notihistory.configuration;

import android.support.v4.app.Fragment;

import org.prezdev.notihistory.MainActivity;
import org.prezdev.notihistory.fragments.AppsFragment;

public class Config {

    public static MainActivity mainActivity;

    public static boolean appItemListAnimation = true;

    // En el constructor de cada fragment, se pregunta por esta variable
    public static boolean fragmentTransition = true;

    public static boolean showSystemApps = false;

    public static Fragment homeScreenFragment = new AppsFragment(Config.mainActivity);
}
