package org.prezdev.notihistory.listeners;

import android.view.View;

import org.prezdev.notihistory.configuration.Config;
import org.prezdev.notihistory.configuration.Preferences;

public class OnFocusChangeSearchListener implements View.OnFocusChangeListener {

    private Preferences preferences;

    public OnFocusChangeSearchListener(){
        preferences = new Preferences();
    }

    @Override
    public void onFocusChange(View view, boolean focusGained) {
        if(focusGained){
            // Desactivar efectos
            preferences.setAppItemListAnimation(false);
        }else{
            // Activar efectos
            preferences.setAppItemListAnimation(true);
        }
    }
}
