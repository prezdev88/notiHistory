package org.prezdev.notihistory.model;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;

import org.prezdev.notihistory.MainActivity;
import org.prezdev.notihistory.R;
import org.prezdev.notihistory.configuration.Config;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Util {
    public static NotificationInstalledApp currentNotificationApp;
    public static Util util;

    public static Util getInstance(){
        if(util == null){
            util = new Util();
        }

        return util;
    }

    public static String getDateFormat(Date date){
        return new SimpleDateFormat("dd 'de' MMM yyyy '-' HH:mm").format(date);
    }

    public static Fragment getVisibleFragment(MainActivity mainActivity){
        FragmentManager fragmentManager = mainActivity.getSupportFragmentManager();

        List<Fragment> fragments = fragmentManager.getFragments();

        if(fragments != null){
            for(Fragment fragment : fragments){
                if(fragment != null && fragment.isVisible())
                    return fragment;
            }
        }
        return null;
    }

    public static String getDatabaseSize(){
        long sizeInBytes = new File(Config.DB_PATH).length();
        float sizeInMegas = ((float)sizeInBytes / 1024 / 1024);

        return  String.format("%.2f", sizeInMegas)+ " Mb";
    }

    public static void showSnackbarMessage(String message, View.OnClickListener onClickListener){
        Snackbar.make(
            MainActivity.getActivity().findViewById(R.id.search_float_icon),
            message,
            Snackbar.LENGTH_LONG
        ).setAction(
            "Action",
            onClickListener
        ).show();
    }

    public static void showSnackbarMessage(String message){
        showSnackbarMessage(message, null);
    }

}
