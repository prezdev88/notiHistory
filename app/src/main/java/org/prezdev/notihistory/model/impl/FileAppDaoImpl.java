package org.prezdev.notihistory.model.impl;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.prezdev.notihistory.configuration.Config;
import org.prezdev.notihistory.model.AppDao;
import org.prezdev.notihistory.model.FileInstalledApp;
import org.prezdev.notihistory.model.InstalledApp;
import org.prezdev.notihistory.model.NotificationInstalledApp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileAppDaoImpl implements AppDao {

    private File installedAppsFile;

    public FileAppDaoImpl() {
        this.installedAppsFile = new File(Config.INSTALLED_APPS_FILE_PATH);
    }

    @Override
    public List<NotificationInstalledApp> getNotificationInstalledApps() {
        return null;
    }

    @Override
    public void save(InstalledApp installedApp) {
        List<InstalledApp> installedApps = getInstalledApps();
        installedApps.add(installedApp);

        save(installedApps);
    }

    private void save(List<InstalledApp> installedApps) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.writeValue(installedAppsFile, installedApps);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String packageName) {
        List<InstalledApp> installedApps = getInstalledApps();

        int removeIndex = -1;
        int index = 0;

        for(InstalledApp installedApp : installedApps){
            if(installedApp.getPackageName().equals(packageName)){
                removeIndex = index;
                break;
            }

            index++;
        }

        if(removeIndex != -1){
            installedApps.remove(removeIndex);
        }

        save(installedApps);
    }

    @Override
    public boolean isAppInDatabase(String packageName) {
        List<InstalledApp> installedApps = getInstalledApps();

        for(InstalledApp installedApp : installedApps){
            if(installedApp.getPackageName().equals(packageName)){
                return true;
            }
        }

        return false;
    }

    private List<InstalledApp> getInstalledApps(){
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            FileInstalledApp fileInstalledApp = objectMapper.readValue(
                installedAppsFile,
                FileInstalledApp.class
            );

            return fileInstalledApp.getInstalledApps();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
}
