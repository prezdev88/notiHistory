package org.prezdev.notihistory.service;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import org.prezdev.notihistory.model.InstalledApp;
import org.prezdev.notihistory.model.NotificationInstalledApp;

import java.util.List;

public interface AppService {
    List<NotificationInstalledApp> getNotificationInstalledApps();

    List<InstalledApp> getInstalledApps(boolean systemAppsIncluded);

    String getAppNameByPackageName(String packageName);

    Drawable getDrawableByPackageName(String packageName) throws PackageManager.NameNotFoundException;

    boolean isSystemPackage(PackageInfo pkgInfo);
}
