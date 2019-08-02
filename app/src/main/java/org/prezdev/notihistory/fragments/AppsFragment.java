package org.prezdev.notihistory.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.prezdev.notihistory.R;
import org.prezdev.notihistory.adapter.AppAdapter;
import org.prezdev.notihistory.listeners.OnItemClickListener;
import org.prezdev.notihistory.listeners.SwipeRefreshAppsListener;
import org.prezdev.notihistory.service.impl.NotificationServiceImpl;

public class AppsFragment extends Fragment {

    private NotificationServiceImpl notificationService;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(
        LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_apps, container, false);

        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setColorSchemeResources(R.color.orange, R.color.green, R.color.blue);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshAppsListener(view));

        notificationService = new NotificationServiceImpl(view.getContext());

        ListView appList = view.findViewById(R.id.appList);

        appList.setOnItemClickListener(new OnItemClickListener(appList, view));

        AppAdapter appAdapter = new AppAdapter(view.getContext(), notificationService.getApps());

        appList.setAdapter(appAdapter);

        return view;
    }




}
