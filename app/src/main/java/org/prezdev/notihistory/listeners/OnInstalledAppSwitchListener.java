package org.prezdev.notihistory.listeners;

import android.view.View;

import org.prezdev.notihistory.model.InstalledApp;

public class OnInstalledAppSwitchListener implements View.OnClickListener {
    private InstalledApp installedApp;
    private OnInstalledAppStateChangeListener onInstalledAppStateChangeListener;

    public OnInstalledAppSwitchListener(
        InstalledApp installedApp,
        OnInstalledAppStateChangeListener onInstalledAppStateChangeListener
    ) {
        this.installedApp = installedApp;
        this.onInstalledAppStateChangeListener = onInstalledAppStateChangeListener;
    }

    @Override
    public void onClick(View view) {
        this.installedApp.toogle();

        onInstalledAppStateChangeListener.stateChange(this.installedApp);
    }
}
