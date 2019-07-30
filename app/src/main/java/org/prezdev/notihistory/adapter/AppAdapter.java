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

import java.util.List;

public class AppAdapter extends BaseAdapter {

    private Context context;
    private List<App> apps;

    public AppAdapter(Context context, List<App> apps) {
        this.context = context;
        this.apps = apps;
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

        ImageView ivIcon = view.findViewById(R.id.ivIcon);
        TextView lblAppName = view.findViewById(R.id.lblAppName);
        TextView lblNotificationCount = view.findViewById(R.id.lblNotificationCount);

        PackageManager packageManager = context.getPackageManager();


        try {
            ivIcon.setImageDrawable(app.getDrawable(packageManager));
        } catch (PackageManager.NameNotFoundException e) {

        }

        lblAppName.setText(app.getName(packageManager));
        lblNotificationCount.setText(String.valueOf(app.getNotificationsCount()));

        return view;
    }
}
