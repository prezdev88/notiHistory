package org.prezdev.notihistory.service;

import org.prezdev.notihistory.model.App;
import org.prezdev.notihistory.model.NotificationApp;
import org.prezdev.notihistory.model.NotificationVO;

import java.util.List;

public interface NotificationService {
    List<NotificationVO> read();

    List<NotificationApp> getNotificationApps();

    List<NotificationVO> findAllByPackageName(String packageName);

    List<App> getInstalledApps(boolean systemAppsIncluded);
}
