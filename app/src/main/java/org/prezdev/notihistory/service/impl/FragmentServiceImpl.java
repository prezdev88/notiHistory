package org.prezdev.notihistory.service.impl;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;

import org.prezdev.notihistory.MainActivity;
import org.prezdev.notihistory.R;
import org.prezdev.notihistory.service.FragmentService;

import java.util.List;

public class FragmentServiceImpl implements FragmentService {
    private FragmentManager fragmentManager;
    private MainActivity mainActivity;
    private DrawerLayout drawerLayout;

    private static FragmentServiceImpl fragmentService;

    private FragmentServiceImpl(MainActivity mainActivity){
        this.mainActivity = mainActivity;
        fragmentManager = mainActivity.getSupportFragmentManager();
        drawerLayout = mainActivity.findViewById(R.id.drawer_layout);
    }

    public static FragmentServiceImpl getInstance(MainActivity mainActivity){
        if(fragmentService == null){
            fragmentService = new FragmentServiceImpl(mainActivity);
        }

        return fragmentService;
    }

    public void load(Fragment fragment){
        fragmentManager
            .beginTransaction()
            .replace(R.id.content, fragment)
            .commit();

        drawerLayout.closeDrawer(GravityCompat.START);
    }

    public Fragment getVisibleFragment(){
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
