package org.prezdev.notihistory.cache;

import org.prezdev.notihistory.MainActivity;
import org.prezdev.notihistory.model.InstalledApp;
import org.prezdev.notihistory.permission.Permisions;
import org.prezdev.notihistory.service.AppService;
import org.prezdev.notihistory.service.impl.AppServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class Cache {
    private static AppService appService;
    private static List<InstalledApp> installedApps;

    static{
        appService = new AppServiceImpl(MainActivity.getActivity());
    }

    public static boolean showSystemAppsSettingsChange;

    public static void updateInstalledAppsCache(){
        try{
            installedApps = appService.getInstalledApps();
        }catch (Exception ex){
            installedApps = new ArrayList();

            Permisions.checkAppPermissions(MainActivity.getActivity());
        }
    }

    public static List<InstalledApp> getInstalledApps(){
        return installedApps;
    }
}
