package org.prezdev.notihistory.listeners;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;

import org.prezdev.notihistory.MainActivity;
import org.prezdev.notihistory.R;
import org.prezdev.notihistory.adapter.AppAdapter;
import org.prezdev.notihistory.fragments.NotificationsFragment;
import org.prezdev.notihistory.model.NotificationInstalledApp;
import org.prezdev.notihistory.model.Util;
import org.prezdev.notihistory.service.impl.NotificationServiceImpl;

public class OnAppClickListener implements AdapterView.OnItemClickListener {

    private NotificationServiceImpl notificationService;
    private MainActivity mainActivity;

    public OnAppClickListener(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toolbar toolbar = mainActivity.findViewById(R.id.toolbar);

        notificationService = NotificationServiceImpl.getInstance(view.getContext());

        AppAdapter appAdapter = (AppAdapter) adapterView.getAdapter();
        NotificationInstalledApp notificationApp = (NotificationInstalledApp) appAdapter.getItem(i);

        Util.currentNotificationApp = notificationApp;

        // lanzar un fragment
        NotificationsFragment notificationsFragment = new NotificationsFragment();

        if(mainActivity != null){
            mainActivity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, notificationsFragment)
                .commit();

            toolbar.collapseActionView();
        }

    }
}
