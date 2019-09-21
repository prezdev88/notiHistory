package org.prezdev.notihistory.model;

import java.util.List;

public interface NotificationDao {
    void save(NotificationVO notificationVO);

    List<NotificationVO> findAll();

    List<NotificationVO> findAllByPackageName(String packageName);

    List<NotificationVO> findAllByQuery(String query);
}
