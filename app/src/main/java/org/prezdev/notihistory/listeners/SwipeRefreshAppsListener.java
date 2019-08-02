package org.prezdev.notihistory.listeners;

import android.content.Context;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ListView;

import org.prezdev.notihistory.R;
import org.prezdev.notihistory.adapter.AppAdapter;
import org.prezdev.notihistory.model.App;
import org.prezdev.notihistory.service.impl.NotificationServiceImpl;

import java.util.List;

public class SwipeRefreshAppsListener implements SwipeRefreshLayout.OnRefreshListener {

    private View view;
    private NotificationServiceImpl notificationService;

    public SwipeRefreshAppsListener(View view) {
        this.view = view;
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                notificationService = new NotificationServiceImpl(view.getContext());
                List<App> apps = notificationService.getApps();

                AppAdapter appAdapter = new AppAdapter(view.getContext(), apps);

                ListView appList = view.findViewById(R.id.appList);

                appList.setAdapter(appAdapter);

                SwipeRefreshLayout swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);

                swipeRefreshLayout.setRefreshing(false);
            }
        }, 1000);
    }
}
