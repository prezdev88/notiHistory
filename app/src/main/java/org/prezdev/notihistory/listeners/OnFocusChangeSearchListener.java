package org.prezdev.notihistory.listeners;

import android.view.View;

import org.prezdev.notihistory.configuration.Config;

public class OnFocusChangeSearchListener implements View.OnFocusChangeListener {
    @Override
    public void onFocusChange(View view, boolean focusGained) {
        if(focusGained){
            // Desactivar efectos
            Config.appItemListAnimation = false;
        }else{
            // Activar efectos
            Config.appItemListAnimation = true;
        }
    }
}
