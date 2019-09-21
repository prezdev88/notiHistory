package org.prezdev.notihistory.service;

import org.prezdev.notihistory.model.InstalledApp;
import org.prezdev.notihistory.model.NotificationInstalledApp;
import org.prezdev.notihistory.model.NotificationVO;

import java.util.List;

public interface NotificationService {
    List<NotificationVO> read();

    List<NotificationVO> findAllByPackageName(String packageName);

    List<InstalledApp> getInstalledApps(boolean systemAppsIncluded);
}
