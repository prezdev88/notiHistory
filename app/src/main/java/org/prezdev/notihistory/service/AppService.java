package org.prezdev.notihistory.service;

import org.prezdev.notihistory.model.NotificationInstalledApp;

import java.util.List;

public interface AppService {
    List<NotificationInstalledApp> getNotificationInstalledApps();
}
