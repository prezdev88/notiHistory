package org.prezdev.notihistory.model.impl;

import android.content.Context;
import android.database.sqlite.SQLiteStatement;

import org.prezdev.notihistory.configuration.Config;
import org.prezdev.notihistory.database.BD;
import org.prezdev.notihistory.database.Connection;
import org.prezdev.notihistory.model.AppDao;
import org.prezdev.notihistory.model.InstalledApp;
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

        connection = new BD(context);
        sqLiteDatabase = connection.getWritableDatabase();
        cursor = sqLiteDatabase.rawQuery(query, null);

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

        sqLiteDatabase.close();

        return notificationApps;
    }

    @Override
    public void save(InstalledApp installedApp) {
        connection = new BD(context);
        sqLiteDatabase = connection.getWritableDatabase();

        String insert = "INSERT INTO app VALUES(null, ?,?)";

        SQLiteStatement statement = sqLiteDatabase.compileStatement(insert);

        statement.bindString(1, installedApp.getName());
        statement.bindString(2, installedApp.getPackageName());

        statement.executeInsert();

        sqLiteDatabase.close();
    }

    @Override
    public void delete(String packageName) {
        connection = new BD(context);
        sqLiteDatabase = connection.getWritableDatabase();

        String delete = "DELETE FROM app WHERE packageName = ?";

        SQLiteStatement statement = sqLiteDatabase.compileStatement(delete);

        statement.bindString(1, packageName);

        statement.executeUpdateDelete();

        sqLiteDatabase.close();
    }

    @Override
    public boolean isAppInDatabase(String packageName) {
        boolean isAppInDatabase;

        connection = new BD(context);
        sqLiteDatabase = connection.getReadableDatabase();

        String select =
            "SELECT * " +
            "FROM app " +
            "WHERE packageName = '"+packageName+"'";

        cursor = sqLiteDatabase.rawQuery(select, null);

        isAppInDatabase = cursor.moveToFirst();

        sqLiteDatabase.close();

        return isAppInDatabase;
    }
}
