package org.prezdev.notihistory.service.impl;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;

import org.prezdev.notihistory.MainActivity;
import org.prezdev.notihistory.R;
import org.prezdev.notihistory.fragments.AppsFragment;
import org.prezdev.notihistory.fragments.InstalledAppsFragment;
import org.prezdev.notihistory.service.AppService;
import org.prezdev.notihistory.service.FragmentService;

import java.util.List;

public class FragmentServiceImpl implements FragmentService {
    private FragmentManager fragmentManager;
    private Activity mainActivity;
    private DrawerLayout drawerLayout;
    private AppService appService;

    public FragmentServiceImpl(Activity activity){
        this.mainActivity = activity;
        fragmentManager = ((MainActivity)activity).getSupportFragmentManager();
        drawerLayout = activity.findViewById(R.id.drawer_layout);
        appService = new AppServiceImpl(activity);
    }

    public void load(Fragment fragment){
        fragmentManager
            .beginTransaction()
            .replace(R.id.content, fragment)
            .commit();

        if(fragment instanceof InstalledAppsFragment){
            mainActivity.setTitle(R.string.installed_apps_title);
        }else if(fragment instanceof AppsFragment){
            mainActivity.setTitle(R.string.my_notifications_apps_title);
        }

        drawerLayout.closeDrawer(GravityCompat.START);
    }

    public Fragment getVisibleFragment(){
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
