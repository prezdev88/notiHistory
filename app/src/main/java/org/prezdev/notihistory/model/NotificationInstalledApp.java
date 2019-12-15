package org.prezdev.notihistory.model;

public class NotificationInstalledApp extends InstalledApp {
    private int notificationsCount;

    public int getNotificationsCount() {
        return notificationsCount;
    }

    public void setNotificationsCount(int notificationsCount) {
        this.notificationsCount = notificationsCount;
    }
}
