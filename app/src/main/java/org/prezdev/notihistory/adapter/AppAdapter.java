package org.prezdev.notihistory.adapter;

import android.content.Context;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.prezdev.notihistory.R;
import org.prezdev.notihistory.model.App;
import org.prezdev.notihistory.model.Util;

import java.util.List;

public class AppAdapter extends BaseAdapter {

    private Context context;
    private List<App> apps;
    private Util util;

    public AppAdapter(Context context, List<App> apps) {
        this.context = context;
        this.apps = apps;
        this.util = new Util(context.getPackageManager());
    }

    @Override
    public int getCount() {
        return apps.size();
    }

    @Override
    public Object getItem(int i) {
        return apps.get(i);
    }

    @Override
    public long getItemId(int i) {
        return apps.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.activity_app_item, null);
        }

        App app = apps.get(i);

        ImageView ivIcon = view.findViewById(R.id.notiIcon);
        TextView lblAppName = view.findViewById(R.id.lblDatetime);
        TextView lblNotificationCount = view.findViewById(R.id.lblNotificationCount);

        try {
            ivIcon.setImageDrawable(util.getDrawableByPackageName(app.getPackageName()));
        } catch (PackageManager.NameNotFoundException e) {

        }

        lblAppName.setText(util.getAppNameByPackageName(app.getPackageName()));
        lblNotificationCount.setText(String.valueOf(app.getNotificationsCount()));

        return view;
    }
}
