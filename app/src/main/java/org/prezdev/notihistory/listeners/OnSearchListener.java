package org.prezdev.notihistory.listeners;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.widget.ListView;

import org.prezdev.notihistory.MainActivity;
import org.prezdev.notihistory.R;
import org.prezdev.notihistory.adapter.AppAdapter;
import org.prezdev.notihistory.fragments.AppsFragment;
import org.prezdev.notihistory.model.App;
import org.prezdev.notihistory.model.Util;
import org.prezdev.notihistory.service.impl.NotificationServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class OnSearchListener implements SearchView.OnQueryTextListener {

    private Util util;
    private MainActivity mainActivity;
    private NotificationServiceImpl notificationService;
    private Context context;

    public OnSearchListener(MainActivity mainActivity){
        this.util = new Util(mainActivity.getApplicationContext().getPackageManager());
        this.notificationService = new NotificationServiceImpl(mainActivity.getApplicationContext());
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
            List<App> apps = notificationService.getApps();
            List<App> search = new ArrayList<>();

            for(App app : apps){
                String appName = util.getAppNameByPackageName(app.getPackageName());
                appName = appName.toLowerCase();

                if(appName.contains(text)){
                    search.add(app);
                }
            }

            AppAdapter appAdapter = new AppAdapter(context, search);

            ListView lvApps = mainActivity.findViewById(R.id.lvApps);
            lvApps.setAdapter(appAdapter);
        }

        return false;
    }
}
