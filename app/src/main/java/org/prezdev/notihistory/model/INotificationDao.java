package org.prezdev.notihistory.model;

import java.util.List;

public interface INotificationDao {
    void save(NotificationVO notificationVO);

    List<NotificationVO> read();

    List<App> getApps();
}
