package org.prezdev.notihistory.service.impl;

import android.content.Context;

import org.prezdev.notihistory.model.App;
import org.prezdev.notihistory.model.NotificationVO;
import org.prezdev.notihistory.model.impl.NotificationDao;
import org.prezdev.notihistory.service.NotificationService;

import java.util.List;

public class NotificationServiceImpl implements NotificationService {

    private NotificationDao notificationDao;

    public NotificationServiceImpl(Context context){
        notificationDao = new NotificationDao(context);
    }

    @Override
    public List<NotificationVO> read() {
        return notificationDao.read();
    }

    @Override
    public List<App> getApps() {
        return notificationDao.getApps();
    }
}
