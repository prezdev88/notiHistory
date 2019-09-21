package org.prezdev.notihistory.model;

import java.util.List;

public interface AppDao {
    List<NotificationInstalledApp> getNotificationInstalledApps();
}
