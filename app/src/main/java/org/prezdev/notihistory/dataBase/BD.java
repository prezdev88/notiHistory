package org.prezdev.notihistory.dataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

// TEXT as ISO8601 strings ("YYYY-MM-DD HH:MM:SS.SSS").
public class BD extends SQLiteOpenHelper{

    private String tNotification =
        "CREATE TABLE notification("+
            "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "color INTEGER," +
            "category TEXT," +
            "postTime TEXT," + //  TEXT as ISO8601 strings ("YYYY-MM-DD HH:MM:SS.SSS")
            "packageName TEXT," +
            "iconId INTEGER," +
            "extraText TEXT," +
            "extraTitle TEXT," +
            "extraSummaryText TEXT," +
            "extraBigText TEXT" +
        ")";

    public BD(Context context, String name, int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tNotification);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /*No implementado*/
    }

}