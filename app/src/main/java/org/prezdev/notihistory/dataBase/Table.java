package org.prezdev.notihistory.dataBase;

public class Table {

    public static final String NOTIFICATION =
        "CREATE TABLE notification("+
        "   id INTEGER PRIMARY KEY AUTOINCREMENT,"+
        "   color INTEGER," +
        "   category TEXT," +
        "   postTime TEXT," + //  TEXT as ISO8601 strings ("YYYY-MM-DD HH:MM:SS.SSS")
        "   packageName TEXT," +
        "   iconId INTEGER," +
        "   extraText TEXT," +
        "   extraTitle TEXT," +
        "   extraSummaryText TEXT," +
        "   extraBigText TEXT" +
        ")";

    public static final String APP =
        "CREATE TABLE app("+
        "   id INTEGER PRIMARY KEY AUTOINCREMENT," +
        "   name TEXT,"+
        "   packageName TEXT," +
        ")";

}
