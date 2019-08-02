package org.prezdev.notihistory.listeners;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.prezdev.notihistory.MainActivity;
import org.prezdev.notihistory.R;
import org.prezdev.notihistory.adapter.AppAdapter;
import org.prezdev.notihistory.adapter.NotificationAdapter;
import org.prezdev.notihistory.fragments.NotificationsFragment;
import org.prezdev.notihistory.model.App;
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
        notificationService = new NotificationServiceImpl(view.getContext());

        AppAdapter appAdapter = (AppAdapter) adapterView.getAdapter();
        App app = (App) appAdapter.getItem(i);

        Util.currentApp = app;

        // lanzar un fragment
        NotificationsFragment notificationsFragment = new NotificationsFragment();

        mainActivity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, notificationsFragment)
                .commit();

    }
}
