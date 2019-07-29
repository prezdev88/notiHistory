package org.prezdev.notihistory.model.impl;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import org.prezdev.notihistory.dataBase.BD;
import org.prezdev.notihistory.model.INotificationDao;
import org.prezdev.notihistory.model.NotificationVO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class NotificationDao implements INotificationDao {
    private Context context;
    private SimpleDateFormat dateFormat;
    private BD connection;
    private SQLiteDatabase db;
    private Cursor cursor;
    private final String DB_PATH =
            Environment.getExternalStorageDirectory().getPath()+"/notiHistory/notiHistory.sqlite";

    public NotificationDao(Context context){
        this.context = context;
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    @Override
    public void save(NotificationVO notificationVO) {
        connection = new BD(context, DB_PATH, 1);
        db = connection.getWritableDatabase();

        String insert = "INSERT INTO notification VALUES(null, ?,?,?,?,?,?,?,?,?)";

        SQLiteStatement statement = db.compileStatement(insert);

        statement.bindString(1, String.valueOf(notificationVO.getColor()));
        statement.bindString(2, notificationVO.getCategory());
        statement.bindString(3, dateFormat.format(notificationVO.getPostTime()));
        statement.bindString(4, notificationVO.getPackageName());
        statement.bindString(5, String.valueOf(notificationVO.getIconId()));
        statement.bindString(6, notificationVO.getExtraText());
        statement.bindString(7, notificationVO.getExtraTitle());
        statement.bindString(8, notificationVO.getExtraSummaryText());
        statement.bindString(9, notificationVO.getExtraBigText());

        statement.executeInsert();

        db.close();
    }

    @Override
    public List<NotificationVO> read() {
        List<NotificationVO> lista = new ArrayList<>();
        NotificationVO notificationVO = null;

        connection = new BD(context, DB_PATH, 1);
        db = connection.getWritableDatabase();

        String select = "SELECT * FROM notification";

        cursor = db.rawQuery(select, null);

        if(cursor.moveToFirst()){
            do{
                notificationVO = new NotificationVO();

                notificationVO.setId(cursor.getInt(0));
                notificationVO.setColor(cursor.getInt(1));
                notificationVO.setCategory(cursor.getString(2));

                try {
                    notificationVO.setPostTime(dateFormat.parse(cursor.getString(3)));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                notificationVO.setPackageName(cursor.getString(4));
                notificationVO.setIconId(cursor.getInt(5));
                notificationVO.setExtraText(cursor.getString(6));
                notificationVO.setExtraTitle(cursor.getString(7));
                notificationVO.setExtraSummaryText(cursor.getString(8));
                notificationVO.setExtraBigText(cursor.getString(9));

                lista.add(notificationVO);
            }while(cursor.moveToNext());
        }

        db.close();

        return lista;
    }
}
