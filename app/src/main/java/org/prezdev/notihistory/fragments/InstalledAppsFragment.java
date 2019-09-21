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
import org.prezdev.notihistory.model.InstalledApp;
import org.prezdev.notihistory.service.NotificationService;
import org.prezdev.notihistory.service.impl.NotificationServiceImpl;

import java.util.List;


public class InstalledAppsFragment extends Fragment {
    private ListView lvInstalledApps;
    private NotificationServiceImpl notificationService;

    @Override
    public View onCreateView(
        LayoutInflater inflater,
        ViewGroup container,
        Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_installed_apps, container, false);
        Context context = view.getContext();

        lvInstalledApps = view.findViewById(R.id.lvInstalledApps);

        lvInstalledApps.setOnItemClickListener(new OnInstalledAppClickListener());

        notificationService = NotificationServiceImpl.getInstance(context);

        List<InstalledApp> installedApps = notificationService.getInstalledApps(false);

        InstalledAppAdapter installedAppAdapter = new InstalledAppAdapter(context, installedApps);

        lvInstalledApps.setAdapter(installedAppAdapter);

        return view;
    }



}
