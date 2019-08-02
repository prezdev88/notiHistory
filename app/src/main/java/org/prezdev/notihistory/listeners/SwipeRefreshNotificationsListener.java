package org.prezdev.notihistory.listeners;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ListView;

import org.prezdev.notihistory.R;
import org.prezdev.notihistory.adapter.AppAdapter;
import org.prezdev.notihistory.adapter.NotificationAdapter;
import org.prezdev.notihistory.model.App;
import org.prezdev.notihistory.model.NotificationVO;
import org.prezdev.notihistory.model.Util;
import org.prezdev.notihistory.service.impl.NotificationServiceImpl;

import java.util.List;

public class SwipeRefreshNotificationsListener implements SwipeRefreshLayout.OnRefreshListener {

    private View view;
    private NotificationServiceImpl notificationService;

    public SwipeRefreshNotificationsListener(View view) {
        this.view = view;
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                notificationService = new NotificationServiceImpl(view.getContext());
                List<NotificationVO> notifications = notificationService.findAllByPackageName(
                    Util.currentApp.getPackageName()
                );

                NotificationAdapter notificationAdapter = new NotificationAdapter(
                    view.getContext(),
                    notifications
                );

                ListView appList = view.findViewById(R.id.appList);

                appList.setAdapter(notificationAdapter);

                SwipeRefreshLayout swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);

                swipeRefreshLayout.setRefreshing(false);
            }
        }, 1000);
    }
}
