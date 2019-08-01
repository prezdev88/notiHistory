package org.prezdev.notihistory.service;

import org.prezdev.notihistory.model.App;
import org.prezdev.notihistory.model.NotificationVO;

import java.util.List;

public interface NotificationService {
    List<NotificationVO> read();

    List<App> getApps();

    List<NotificationVO> findAllByPackageName(String packageName);
}
