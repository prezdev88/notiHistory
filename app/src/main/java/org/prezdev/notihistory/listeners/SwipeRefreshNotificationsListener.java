package org.prezdev.notihistory.listeners;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ListView;

import org.prezdev.notihistory.R;
import org.prezdev.notihistory.adapter.NotificationAdapter;
import org.prezdev.notihistory.model.NotificationVO;
import org.prezdev.notihistory.model.Util;
import org.prezdev.notihistory.service.impl.NotificationServiceImpl;

import java.util.List;

public class SwipeRefreshNotificationsListener implements SwipeRefreshLayout.OnRefreshListener, Runnable {

    private View view;
    private NotificationServiceImpl notificationService;
    private SwipeRefreshLayout notificationsSwipeRefresh;
    private ListView lvNotifications;

    public SwipeRefreshNotificationsListener(View view) {
        this.view = view;
        notificationsSwipeRefresh = view.findViewById(R.id.notificationsSwipeRefresh);
        lvNotifications = view.findViewById(R.id.lvNotifications);
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(this, 500);
    }

    @Override
    public void run() {
        // Se obtiene el servicio de notificaciones
        notificationService = new NotificationServiceImpl(view.getContext());

        // Se obtienen las notificaciones de la aplicación actual
        List<NotificationVO> notifications = notificationService.findAllByPackageName(
            Util.currentApp.getPackageName()
        );

        // Se crea el adapter
        NotificationAdapter notificationAdapter = new NotificationAdapter(
            view.getContext(),
            notifications
        );

        // Colocando las notificaciones en el listView
        lvNotifications.setAdapter(notificationAdapter);

        // Parando animación de load
        notificationsSwipeRefresh.setRefreshing(false);
    }
}
