package org.prezdev.notihistory.service.impl;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import org.prezdev.notihistory.model.InstalledApp;
import org.prezdev.notihistory.model.NotificationInstalledApp;
import org.prezdev.notihistory.model.impl.AppDaoImpl;
import org.prezdev.notihistory.service.AppService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AppServiceImpl implements AppService {

    private AppDaoImpl appDao;
    private Context context;
    private PackageManager packageManager;

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

    @Override
    public List<InstalledApp> getInstalledApps(boolean showSystemApps) {
        List<InstalledApp> installedApps = new ArrayList<>();
        packageManager = this.context.getPackageManager();

        InstalledApp installedApp;
        String appName;
        String packageName;

        List<PackageInfo> packs = packageManager.getInstalledPackages(0);

        int id = 1;
        for(PackageInfo packageInfo : packs){
            // No hay que incluir las apps del sistema?
            if(!showSystemApps){
                // la app es del sistema
                if(isSystemPackage(packageInfo)){
                    continue;
                }
            }

            installedApp = new InstalledApp();

            packageName = packageInfo.packageName;

            installedApp.setId(id++);
            installedApp.setPackageName(packageName);
            appName = getAppNameByPackageName(packageName);
            installedApp.setName(appName);

            try {
                installedApp.setIcon(getDrawableByPackageName(packageName));
            } catch (PackageManager.NameNotFoundException e) { }

            installedApp.setVersionName(packageInfo.versionName);
            installedApp.setVersionCode(packageInfo.versionCode);

            installedApp.setSelected(isAppInDatabase(packageName));

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

    @Override
    public String getAppNameByPackageName(String packageName){
        ApplicationInfo applicationInfo;
        try {
            applicationInfo = packageManager.getApplicationInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            applicationInfo = null;
        }
        return (String) (applicationInfo != null ? packageManager.getApplicationLabel(applicationInfo) : "App sin nombre");
    }

    @Override
    public Drawable getDrawableByPackageName(String packageName) throws PackageManager.NameNotFoundException {
        return packageManager.getApplicationIcon(packageName);
    }

    @Override
    public boolean isSystemPackage(PackageInfo pkgInfo) {
        return !(packageManager.getLaunchIntentForPackage(pkgInfo.packageName) != null);

        // return ((pkgInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0);
    }

    /*------------------- Apps que el usuario escogió en el Fragment -------------------*/
    @Override
    public void save(InstalledApp installedApp) {
        appDao.save(installedApp);
    }

    @Override
    public void delete(String packageName) {
        appDao.delete(packageName);
    }

    @Override
    public boolean isAppInDatabase(String packageName) {
        return appDao.isAppInDatabase(packageName);
    }
    /*------------------- Apps que el usuario escogió en el Fragment -------------------*/
}
