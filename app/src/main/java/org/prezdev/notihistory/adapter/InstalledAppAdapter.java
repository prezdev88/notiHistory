package org.prezdev.notihistory.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import org.prezdev.notihistory.R;
import org.prezdev.notihistory.animations.Animation;
import org.prezdev.notihistory.configuration.Preferences;
import org.prezdev.notihistory.listeners.OnInstalledAppStateChangeListener;
import org.prezdev.notihistory.listeners.OnInstalledAppSwitchListener;
import org.prezdev.notihistory.model.InstalledApp;

import java.util.ArrayList;
import java.util.List;

public class InstalledAppAdapter extends BaseAdapter {
    private Context context;
    private List<InstalledApp> installedApps;
    private ArrayList<InstalledApp> clonedInstalledApps; // Used by filter listview
    private OnInstalledAppStateChangeListener onInstalledAppStateChangeListener;
    private Preferences preferences;

    public InstalledAppAdapter(
        Context context,
        List<InstalledApp> installedApps,
        OnInstalledAppStateChangeListener onInstalledAppStateChangeListener
    ) {
        this.context = context;
        this.installedApps = installedApps;
        this.clonedInstalledApps = new ArrayList<>(installedApps);
        this.onInstalledAppStateChangeListener = onInstalledAppStateChangeListener;
        this.preferences = new Preferences();
    }

    @Override
    public int getCount() {
        return this.installedApps.size();
    }

    @Override
    public Object getItem(int i) {
        return this.installedApps.get(i);
    }

    @Override
    public long getItemId(int i) {
        return this.installedApps.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE
            );

            view = inflater.inflate(R.layout.activity_installed_app_item, null);
        }

        InstalledApp installedApp = this.installedApps.get(i);

        ImageView appInstalledIcon = view.findViewById(R.id.appInstalledIcon);
        TextView lblAppInstalledName = view.findViewById(R.id.lblAppInstalledName);
        TextView lblAppPackageName = view.findViewById(R.id.lblAppPackageName);
        TextView lblAppVersion = view.findViewById(R.id.lblAppVersion);
        Switch chkAddApp = view.findViewById(R.id.chkAddApp);

        chkAddApp.setOnClickListener(new OnInstalledAppSwitchListener(
            installedApp,
            this.onInstalledAppStateChangeListener
        ));

        appInstalledIcon.setImageDrawable(installedApp.getIcon());
        lblAppInstalledName.setText(installedApp.getName());
        lblAppPackageName.setText(installedApp.getPackageName());
        lblAppVersion.setText("v"+installedApp.getVersionName());

        chkAddApp.setChecked(installedApp.isSelected());

        if(preferences.isAppItemListAnimation()){
            Animation.apply(view);
        }

        return view;
    }

    public void filter(String text) {
        text = text.toLowerCase();
        List<InstalledApp> aux = new ArrayList<>();

        if (text.length() == 0) {
            aux.addAll(clonedInstalledApps);
        } else {
            for (InstalledApp installedApp : clonedInstalledApps) {
                if (installedApp.getName().toLowerCase().contains(text)) {
                    aux.add(installedApp);
                }
            }
        }

        installedApps.clear();
        installedApps.addAll(aux);

        notifyDataSetChanged();
    }
}
