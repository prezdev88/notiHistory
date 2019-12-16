package org.prezdev.notihistory.listeners.search;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.prezdev.notihistory.MainActivity;
import org.prezdev.notihistory.R;
import org.prezdev.notihistory.adapter.AppAdapter;
import org.prezdev.notihistory.adapter.InstalledAppAdapter;
import org.prezdev.notihistory.fragments.AppsFragment;
import org.prezdev.notihistory.fragments.InstalledAppsFragment;
import org.prezdev.notihistory.model.InstalledApp;
import org.prezdev.notihistory.model.NotificationInstalledApp;
import org.prezdev.notihistory.model.Util;
import org.prezdev.notihistory.service.AppService;
import org.prezdev.notihistory.service.impl.AppServiceImpl;
import org.prezdev.notihistory.service.impl.NotificationServiceImpl;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class OnSearchListener implements SearchView.OnQueryTextListener {

    private MainActivity mainActivity;
    private AppService appService;
    private Context context;

    public OnSearchListener(MainActivity mainActivity){
        this.appService = new AppServiceImpl(mainActivity);
        this.mainActivity = mainActivity;
        this.context = mainActivity.getApplicationContext();
    }

    @Override
    public boolean onQueryTextSubmit(String text) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String text) {
        text = text.toLowerCase();

        Fragment visibleFragment = Util.getVisibleFragment(mainActivity);

        if(visibleFragment instanceof AppsFragment){
            ListView lvApps = mainActivity.findViewById(R.id.lvApps);

            AppAdapter adapter = (AppAdapter)lvApps.getAdapter();

            adapter.filter(text);
        }else if(visibleFragment instanceof InstalledAppsFragment){
            ListView lvInstalledApps = mainActivity.findViewById(R.id.lvInstalledApps);

            InstalledAppAdapter adapter = (InstalledAppAdapter)lvInstalledApps.getAdapter();

            adapter.filter(text);
        }

        return false;
    }
}
