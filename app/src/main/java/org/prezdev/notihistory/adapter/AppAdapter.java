package org.prezdev.notihistory.adapter;

import android.content.Context;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.prezdev.notihistory.R;
import org.prezdev.notihistory.configuration.Config;
import org.prezdev.notihistory.configuration.Preferences;
import org.prezdev.notihistory.model.NotificationInstalledApp;
import org.prezdev.notihistory.model.Util;
import org.prezdev.notihistory.service.AppService;
import org.prezdev.notihistory.service.impl.AppServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class AppAdapter extends BaseAdapter {

    private Context context;
    private List<NotificationInstalledApp> notificationApps;
    private ArrayList<NotificationInstalledApp> clonedNotificationApps; // Used by filter listview
    private AppService appService;
    private Preferences preferences;

    public AppAdapter(Context context, List<NotificationInstalledApp> notificationApps) {
        this.context = context;
        this.notificationApps = notificationApps;
        this.clonedNotificationApps = new ArrayList<>(notificationApps);
        this.appService = new AppServiceImpl(context);
        this.preferences = new Preferences();
    }

    @Override
    public int getCount() {
        return notificationApps.size();
    }

    @Override
    public Object getItem(int i) {
        return notificationApps.get(i);
    }

    @Override
    public long getItemId(int i) {
        return notificationApps.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.activity_app_item, null);
        }

        NotificationInstalledApp notificationApp = notificationApps.get(i);

        ImageView ivIcon = view.findViewById(R.id.appIcon);
        TextView lblAppName = view.findViewById(R.id.lblAppName);
        TextView lblNotificationCount = view.findViewById(R.id.lblNotificationCount);

        try {
            ivIcon.setImageDrawable(appService.getDrawableByPackageName(notificationApp.getPackageName()));
        } catch (PackageManager.NameNotFoundException e) {

        }

        lblAppName.setText(appService.getAppNameByPackageName(notificationApp.getPackageName()));
        lblNotificationCount.setText(String.valueOf(notificationApp.getNotificationsCount()));

        // ivIcon.setAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_in_left));
        // lblAppName.setAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_in_left));

        if(preferences.isAppItemListAnimation()){
            view.setAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_in_left));
        }

        return view;
    }

    public void filter(String text) {
        text = text.toLowerCase();
        List<NotificationInstalledApp> aux = new ArrayList<>();

        if (text.length() == 0) {
            aux.addAll(clonedNotificationApps);
        } else {
            for (NotificationInstalledApp notificationInstalledApp : clonedNotificationApps) {

                String appName = appService.getAppNameByPackageName(notificationInstalledApp.getPackageName());
                appName = appName.toLowerCase();

                if (appName.contains(text)) {
                    aux.add(notificationInstalledApp);
                }
            }
        }

        notificationApps.clear();
        notificationApps.addAll(aux);

        notifyDataSetChanged();
    }
}
