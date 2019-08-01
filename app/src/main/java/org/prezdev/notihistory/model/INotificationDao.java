package org.prezdev.notihistory.model;

import java.util.List;

public interface INotificationDao {
    void save(NotificationVO notificationVO);

    List<NotificationVO> findAll();

    List<App> getApps();

    List<NotificationVO> findAllByPackageName(String packageName);

    List<NotificationVO> findAllByQuery(String query);
}
