package org.prezdev.notihistory.model.impl;

import android.content.Context;

import org.prezdev.notihistory.dataBase.BD;
import org.prezdev.notihistory.dataBase.Connection;
import org.prezdev.notihistory.model.AppDao;
import org.prezdev.notihistory.model.NotificationInstalledApp;

import java.util.ArrayList;
import java.util.List;

public class AppDaoImpl extends Connection implements AppDao {

    public AppDaoImpl(Context context) {
        super(context);
    }

    @Override
    public List<NotificationInstalledApp> getNotificationInstalledApps() {
        List<NotificationInstalledApp> notificationApps = new ArrayList<>();

        String query =
            "SELECT DISTINCT(packageName), COUNT(*), id " +
            "FROM notification " +
            "WHERE extraText != '' " +
            "GROUP BY packageName ORDER BY postTime DESC";

        connection = new BD(context, DB_PATH, 1);
        db = connection.getWritableDatabase();
        cursor = db.rawQuery(query, null);

        NotificationInstalledApp notificationApp;
        if(cursor.moveToFirst()){
            do{
                notificationApp = new NotificationInstalledApp();

                notificationApp.setPackageName(cursor.getString(0));
                notificationApp.setNotificationsCount(cursor.getInt(1));
                notificationApp.setId(cursor.getInt(2));

                notificationApps.add(notificationApp);
            }while(cursor.moveToNext());
        }

        db.close();

        return notificationApps;
    }
}
