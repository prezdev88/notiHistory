package org.prezdev.notihistory.dataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.text.SimpleDateFormat;

public class Connection {
    protected Context context;
    protected SimpleDateFormat dateFormat;
    protected BD connection;
    protected SQLiteDatabase db;
    protected Cursor cursor;
    protected final String DB_PATH =
            Environment.getExternalStorageDirectory().getPath()+"/notiHistory/notiHistory.sqlite";

    public Connection(Context context) {
        this.context = context;
    }
}
