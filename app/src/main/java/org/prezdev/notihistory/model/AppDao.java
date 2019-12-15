package org.prezdev.notihistory.model;

import java.util.List;

public interface AppDao {
    List<NotificationInstalledApp> getNotificationInstalledApps();

    void save(InstalledApp installedApp);

    void delete(String packageName);

    boolean isAppInDatabase(String packageName);
}
