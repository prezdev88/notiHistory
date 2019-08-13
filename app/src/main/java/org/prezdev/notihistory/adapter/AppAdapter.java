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
import org.prezdev.notihistory.model.NotificationApp;
import org.prezdev.notihistory.model.Util;

import java.util.List;

public class AppAdapter extends BaseAdapter {

    private Context context;
    private List<NotificationApp> notificationApps;
    private Util util;

    public AppAdapter(Context context, List<NotificationApp> notificationApps) {
        this.context = context;
        this.notificationApps = notificationApps;
        this.util = Util.getInstance(context.getPackageManager());
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

        NotificationApp notificationApp = notificationApps.get(i);

        ImageView ivIcon = view.findViewById(R.id.notiIcon);
        TextView lblAppName = view.findViewById(R.id.lblDatetime);
        TextView lblNotificationCount = view.findViewById(R.id.lblNotificationCount);

        try {
            ivIcon.setImageDrawable(util.getDrawableByPackageName(notificationApp.getPackageName()));
        } catch (PackageManager.NameNotFoundException e) {

        }

        lblAppName.setText(util.getAppNameByPackageName(notificationApp.getPackageName()));
        lblNotificationCount.setText(String.valueOf(notificationApp.getNotificationsCount()));

        // ivIcon.setAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_in_left));
        // lblAppName.setAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_in_left));

        if(Config.appItemListAnimation){
            view.setAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_in_left));
        }

        return view;
    }
}
