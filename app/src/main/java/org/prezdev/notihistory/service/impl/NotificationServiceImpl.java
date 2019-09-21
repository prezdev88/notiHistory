package org.prezdev.notihistory.service.impl;

import android.content.Context;
import android.content.pm.ApplicationInfo;
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
    public List<NotificationInstalledApp> getNotificationApps() {
        return notificationDao.getApps();
    }

    @Override
    public List<NotificationVO> findAllByPackageName(String packageName) {
        return notificationDao.findAllByPackageName(packageName);
    }

    @Override
    public List<InstalledApp> getInstalledApps(boolean systemAppsIncluded) {
        List<InstalledApp> installedApps = new ArrayList<>();
        PackageManager packageManager = this.context.getPackageManager();

        InstalledApp installedApp;
        String appName;
        String packageName;
        Util util = Util.getInstance(packageManager);

        List<PackageInfo> packs = packageManager.getInstalledPackages(0);

        int id = 1;
        for(PackageInfo packageInfo : packs){
            // No hay que incluir las apps del systema?
            if(!systemAppsIncluded){
                // la app es del sistema
                if(util.isSystemPackage(packageInfo)){
                    continue;
                }
            }

            installedApp = new InstalledApp();

            packageName = packageInfo.packageName;

            installedApp.setId(id++);
            installedApp.setPackageName(packageName);
            appName = util.getAppNameByPackageName(packageName);
            installedApp.setName(appName);

            try {
                installedApp.setIcon(util.getDrawableByPackageName(packageName));
            } catch (PackageManager.NameNotFoundException e) { }

            installedApp.setVersionName(packageInfo.versionName);
            installedApp.setVersionCode(packageInfo.versionCode);

            installedApps.add(installedApp);
        }

        Comparator<InstalledApp> installedAppComparator = new Comparator<InstalledApp>() {
            @Override
            public int compare(InstalledApp installedApp1, InstalledApp installedApp2) {
                return installedApp1.getName().compareTo(installedApp2.getName());
            }
        };

        Collections.sort(installedApps, installedAppComparator);

        return installedApps;
    }
}
