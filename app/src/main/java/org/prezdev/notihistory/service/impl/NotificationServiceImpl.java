package org.prezdev.notihistory.service.impl;

import android.content.Context;

import org.prezdev.notihistory.model.NotificationVO;
import org.prezdev.notihistory.model.impl.NotificationDaoImpl;
import org.prezdev.notihistory.service.NotificationService;

import java.util.List;

public class NotificationServiceImpl implements NotificationService {

    private NotificationDaoImpl notificationDao;

    public NotificationServiceImpl(Context context){
        notificationDao = new NotificationDaoImpl(context);
    }

    @Override
    public List<NotificationVO> read() {
        return notificationDao.findAll();
    }

    @Override
    public List<NotificationVO> findAllByPackageName(String packageName) {
        return notificationDao.findAllByPackageName(packageName);
    }
}
