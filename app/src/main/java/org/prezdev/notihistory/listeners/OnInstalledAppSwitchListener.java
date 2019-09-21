package org.prezdev.notihistory.listeners;

import android.view.View;
import android.widget.CompoundButton;

import org.prezdev.notihistory.model.InstalledApp;

public class OnInstalledAppSwitchListener implements View.OnClickListener {
    private InstalledApp installedApp;

    public OnInstalledAppSwitchListener(InstalledApp installedApp) {
        this.installedApp = installedApp;
    }

    @Override
    public void onClick(View view) {
        this.installedApp.toogle();
    }
}
