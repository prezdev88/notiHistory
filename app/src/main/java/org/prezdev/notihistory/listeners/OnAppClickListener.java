package org.prezdev.notihistory.listeners;

import android.app.Activity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;

import org.prezdev.notihistory.MainActivity;
import org.prezdev.notihistory.R;
import org.prezdev.notihistory.adapter.AppAdapter;
import org.prezdev.notihistory.fragments.NotificationsFragment;
import org.prezdev.notihistory.model.NotificationInstalledApp;
import org.prezdev.notihistory.model.Util;
import org.prezdev.notihistory.service.FragmentService;
import org.prezdev.notihistory.service.NotificationService;
import org.prezdev.notihistory.service.impl.FragmentServiceImpl;
import org.prezdev.notihistory.service.impl.NotificationServiceImpl;

public class OnAppClickListener implements AdapterView.OnItemClickListener {

    private NotificationService notificationService;
    private FragmentService fragmentService;
    private Activity activity;

    public OnAppClickListener(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toolbar toolbar = activity.findViewById(R.id.toolbar);

        notificationService = new NotificationServiceImpl(view.getContext());
        fragmentService = new FragmentServiceImpl(activity);

        AppAdapter appAdapter = (AppAdapter) adapterView.getAdapter();
        NotificationInstalledApp notificationApp = (NotificationInstalledApp) appAdapter.getItem(i);

        Util.currentNotificationApp = notificationApp;

        // lanzar fragment de las notificaciones de esa app
        NotificationsFragment notificationsFragment = new NotificationsFragment();

        // TODO: Se cae acá aveces, cuando cierro la app, la vuelvo a abrir y pincho una app
        // para ver las notificaciones
        fragmentService.load(notificationsFragment);

        toolbar.collapseActionView();
    }
}
