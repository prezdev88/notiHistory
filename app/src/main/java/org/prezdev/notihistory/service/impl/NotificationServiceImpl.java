package org.prezdev.notihistory.service.impl;

import android.content.Context;

import org.prezdev.notihistory.model.NotificationVO;
import org.prezdev.notihistory.model.impl.NotificationDaoImpl;
import org.prezdev.notihistory.service.NotificationService;

import java.util.List;

public class NotificationServiceImpl implements NotificationService {

    private NotificationDaoImpl notificationDao;
    private Context context;
    private static NotificationServiceImpl notificationService;

    public static NotificationServiceImpl getInstance(Context context){
        if(notificationService == null){
            notificationService = new NotificationServiceImpl(context);
        }

        return notificationService;
    }

    private NotificationServiceImpl(Context context){
        notificationDao = new NotificationDaoImpl(context);
        this.context = context;
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
