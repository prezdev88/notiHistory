package org.prezdev.notihistory.animations;

import android.support.v4.app.Fragment;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Gravity;

import org.prezdev.notihistory.configuration.Preferences;

public class Transition {
    private static Preferences preferences;

    static{
        preferences = new Preferences();
    }

    public static void apply(Fragment fragment){
        long transitionDuration = preferences.getTransitionDuration();
        fragment.setExitTransition(new Fade());
        fragment.setEnterTransition(new Slide(Gravity.LEFT).setDuration(transitionDuration));
    }
}
