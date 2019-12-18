package org.prezdev.notihistory.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.prezdev.notihistory.configuration.Config;

// TEXT as ISO8601 strings ("YYYY-MM-DD HH:MM:SS.SSS").
public class BD extends SQLiteOpenHelper{

    private static final int DB_VERSION = 2;

    public BD(Context context) {
        super(context, Config.DB_PATH, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try{
            sqLiteDatabase.execSQL(Table.NOTIFICATION);
            sqLiteDatabase.execSQL(Table.APP);
        }catch(Exception ex){}
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        try{
            switch (newVersion){
                case 2:
                    sqLiteDatabase.execSQL(Table.APP);

                    break;
            }
        }catch(Exception ex){}
    }

}