package org.prezdev.notihistory.configuration;

import android.os.Environment;
import android.support.v4.app.Fragment;

import org.prezdev.notihistory.fragments.AppsFragment;

public class Config {
    public static Fragment homeScreenFragment = new AppsFragment();

    public final static String DB_PATH =
        Environment.getExternalStorageDirectory().getPath()+"/notiHistory/notiHistory.sqlite";

    public final static String INSTALLED_APPS_FILE_PATH =
            Environment.getExternalStorageDirectory().getPath()+"/notiHistory/installedApps.json";

    public final static String NOTIFICATIONS_FILE_PATH =
            Environment.getExternalStorageDirectory().getPath()+"/notiHistory/notifications.json";

    public static boolean notificationConfigDialogIsVisible;
}
