package org.prezdev.notihistory.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.prezdev.notihistory.R;
import org.prezdev.notihistory.adapter.AppAdapter;
import org.prezdev.notihistory.service.impl.NotificationServiceImpl;

public class AppsFragment extends Fragment {

    private NotificationServiceImpl notificationService;

    @Override
    public View onCreateView(
        LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_apps, container, false);

        notificationService = new NotificationServiceImpl(view.getContext());

        ListView appList = view.findViewById(R.id.appList);

        AppAdapter appAdapter = new AppAdapter(view.getContext(), notificationService.getApps());

        appList.setAdapter(appAdapter);

        return view;
    }


}
