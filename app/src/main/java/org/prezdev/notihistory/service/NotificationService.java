package org.prezdev.notihistory.service;

import org.prezdev.notihistory.model.NotificationVO;

import java.util.List;

public interface NotificationService {
    List<NotificationVO> read();

    List<NotificationVO> findAllByPackageName(String packageName);

    void loadFileNotifications();
}
