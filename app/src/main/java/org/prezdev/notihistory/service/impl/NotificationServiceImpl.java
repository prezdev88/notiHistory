package org.prezdev.notihistory.service.impl;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import org.prezdev.notihistory.model.InstalledApp;
import org.prezdev.notihistory.model.NotificationInstalledApp;
import org.prezdev.notihistory.model.NotificationVO;
import org.prezdev.notihistory.model.Util;
import org.prezdev.notihistory.model.impl.NotificationDao;
import org.prezdev.notihistory.service.NotificationService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class NotificationServiceImpl implements NotificationService {

    private NotificationDao notificationDao;
    private Context context;
    private static NotificationServiceImpl notificationService;

    public static NotificationServiceImpl getInstance(Context context){
        if(notificationService == null){
            notificationService = new NotificationServiceImpl(context);
        }

        return notificationService;
    }

    private NotificationServiceImpl(Context context){
        notificationDao = new NotificationDao(context);
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
