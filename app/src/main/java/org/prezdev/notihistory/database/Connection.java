package org.prezdev.notihistory.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.text.SimpleDateFormat;

public class Connection {
    protected Context context;
    protected SimpleDateFormat dateFormat;
    protected BD connection;
    protected SQLiteDatabase sqLiteDatabase;
    protected Cursor cursor;

    public Connection(Context context) {
        this.context = context;
    }
}
