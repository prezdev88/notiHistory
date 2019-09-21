package org.prezdev.notihistory.service.impl;

import android.content.Context;

import org.prezdev.notihistory.model.NotificationInstalledApp;
import org.prezdev.notihistory.model.impl.AppDaoImpl;
import org.prezdev.notihistory.service.AppService;

import java.util.List;

public class AppServiceImpl implements AppService {

    private AppDaoImpl appDao;
    private Context context;
    private static AppServiceImpl appService;

    public static AppServiceImpl getInstance(Context context){
        if(appService == null){
            appService = new AppServiceImpl(context);
        }

        return appService;
    }

    private AppServiceImpl(Context context){
        appDao = new AppDaoImpl(context);
        this.context = context;
    }

    @Override
    public List<NotificationInstalledApp> getNotificationInstalledApps() {
        return appDao.getNotificationInstalledApps();
    }
}
