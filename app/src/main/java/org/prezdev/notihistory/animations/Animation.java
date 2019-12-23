package org.prezdev.notihistory.animations;

import android.view.View;
import android.view.animation.AnimationUtils;

import org.prezdev.notihistory.MainActivity;
import org.prezdev.notihistory.R;
import org.prezdev.notihistory.configuration.Preferences;

public class Animation {
    private static Preferences preferences;

    static{
        preferences = new Preferences();
    }

    public static void apply(View view){
        long animationDuration = preferences.getAnimationDuration();

        android.view.animation.Animation animation = AnimationUtils.loadAnimation(
            MainActivity.getActivity(),
            R.anim.slide_in_left
        );

        animation.setDuration(animationDuration);

        view.setAnimation(animation);
    }
}
