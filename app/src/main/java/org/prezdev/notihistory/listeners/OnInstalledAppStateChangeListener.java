package org.prezdev.notihistory.listeners;

import org.prezdev.notihistory.model.InstalledApp;

public interface OnInstalledAppStateChangeListener {
    void stateChange(InstalledApp installedApp);
}
