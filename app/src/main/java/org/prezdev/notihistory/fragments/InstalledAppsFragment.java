package org.prezdev.notihistory.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.prezdev.notihistory.R;
import org.prezdev.notihistory.adapter.InstalledAppAdapter;
import org.prezdev.notihistory.listeners.OnInstalledAppClickListener;
import org.prezdev.notihistory.listeners.OnInstalledAppLongClickListener;
import org.prezdev.notihistory.listeners.OnInstalledAppStateChangeListener;
import org.prezdev.notihistory.model.InstalledApp;
import org.prezdev.notihistory.service.impl.AppServiceImpl;

import java.util.List;


public class InstalledAppsFragment extends Fragment implements OnInstalledAppStateChangeListener {
    private ListView lvInstalledApps;
    private AppServiceImpl appService;

    @Override
    public View onCreateView(
        LayoutInflater inflater,
        ViewGroup container,
        Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_installed_apps, container, false);
        Context context = view.getContext();

        lvInstalledApps = view.findViewById(R.id.lvInstalledApps);

        lvInstalledApps.setOnItemClickListener(new OnInstalledAppClickListener(this));
        lvInstalledApps.setOnItemLongClickListener(new OnInstalledAppLongClickListener());

        appService = AppServiceImpl.getInstance(context);

        List<InstalledApp> installedApps = appService.getInstalledApps(false);

        InstalledAppAdapter installedAppAdapter = new InstalledAppAdapter(
            context, installedApps, this
        );

        lvInstalledApps.setAdapter(installedAppAdapter);

        return view;
    }


    @Override
    public void stateChange(InstalledApp installedApp) {
        if(installedApp.isSelected()){
            appService.save(installedApp);
        }else{
            appService.delete(installedApp.getPackageName());
        }
    }
}
