package org.prezdev.notihistory.service.impl;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import org.prezdev.notihistory.model.App;
import org.prezdev.notihistory.model.NotificationApp;
import org.prezdev.notihistory.model.NotificationVO;
import org.prezdev.notihistory.model.Util;
import org.prezdev.notihistory.model.impl.NotificationDao;
import org.prezdev.notihistory.service.NotificationService;

import java.util.ArrayList;
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
    public List<NotificationApp> getNotificationApps() {
        return notificationDao.getApps();
    }

    @Override
    public List<NotificationVO> findAllByPackageName(String packageName) {
        return notificationDao.findAllByPackageName(packageName);
    }

    @Override
    public List<App> getInstalledApps(boolean systemAppsIncluded) {
        List<App> installedApps = new ArrayList<>();
        PackageManager packageManager = this.context.getPackageManager();

        List<ApplicationInfo> installedApplications = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);

        App app;
        String appName;
        String packageName;
        Util util = Util.getInstance(packageManager);

        List<PackageInfo> packs = packageManager.getInstalledPackages(0);

        for(PackageInfo packageInfo : packs){

            // No hay que incluir las apps del systema?
            if(!systemAppsIncluded){
                // la app es del sistema
                if(util.isSystemPackage(packageInfo)){
                    continue;
                }
            }

            app = new App();

            packageName = packageInfo.packageName;

            app.setPackageName(packageName);
            appName = util.getAppNameByPackageName(packageName);
            app.setName(appName);

            try {
                app.setIcon(util.getDrawableByPackageName(packageName));
            } catch (PackageManager.NameNotFoundException e) { }

            app.setVersionName(packageInfo.versionName);
            app.setVersionCode(packageInfo.versionCode);

            installedApps.add(app);

        }

        return installedApps;
    }
}
