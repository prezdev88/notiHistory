package org.prezdev.notihistory.dataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

// TEXT as ISO8601 strings ("YYYY-MM-DD HH:MM:SS.SSS").
public class BD extends SQLiteOpenHelper{

    public BD(Context context, String name, int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Table.NOTIFICATION);
        sqLiteDatabase.execSQL(Table.APP);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        /*No implementado*/
    }

}