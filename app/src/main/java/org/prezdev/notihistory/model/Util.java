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
    private PackageManager packageManager;
    public static NotificationInstalledApp currentNotificationApp;
    public static Util util;

    public static Util getInstance(PackageManager packageManager){
        if(util == null){
            util = new Util(packageManager);
        }

        return util;
    }

    private Util(PackageManager packageManager) {
        this.packageManager = packageManager;
    }

    public Drawable getDrawableByPackageName(String packageName) throws PackageManager.NameNotFoundException {
        return packageManager.getApplicationIcon(packageName);
    }

    public static String getDateFormat(Date date){
        return new SimpleDateFormat("dd 'de' MMM yyyy '-' HH:mm").format(date);
    }

    public String getAppNameByPackageName(String packageName){
        ApplicationInfo applicationInfo;
        try {
            applicationInfo = packageManager.getApplicationInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            applicationInfo = null;
        }
        return (String) (applicationInfo != null ? packageManager.getApplicationLabel(applicationInfo) : "App sin nombre");
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

    public boolean isSystemPackage(PackageInfo pkgInfo) {
        return ((pkgInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0);
    }
}
