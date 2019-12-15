package org.prezdev.notihistory.model;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import org.prezdev.notihistory.MainActivity;

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


}
