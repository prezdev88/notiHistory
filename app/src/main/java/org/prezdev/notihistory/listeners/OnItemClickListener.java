package org.prezdev.notihistory.listeners;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

import org.prezdev.notihistory.R;
import org.prezdev.notihistory.adapter.AppAdapter;
import org.prezdev.notihistory.adapter.NotificationAdapter;
import org.prezdev.notihistory.model.App;
import org.prezdev.notihistory.model.Util;
import org.prezdev.notihistory.service.impl.NotificationServiceImpl;

public class OnItemClickListener implements AdapterView.OnItemClickListener {

    private NotificationServiceImpl notificationService;
    private NotificationAdapter notificationAdapter;
    private ListView appList;
    private SwipeRefreshLayout swipeRefreshLayout;
    private View mainView;

    public OnItemClickListener(ListView appList, View mainView) {
        this.appList = appList;
        this.mainView = mainView;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        notificationService = new NotificationServiceImpl(view.getContext());
        Adapter adapter = adapterView.getAdapter();

        if(adapter instanceof AppAdapter){
            AppAdapter appAdapter = (AppAdapter) adapterView.getAdapter();

            App app = (App) appAdapter.getItem(i);

            Util.currentApp = app;

            notificationAdapter = new NotificationAdapter(
                view.getContext(),
                notificationService.findAllByPackageName(app.getPackageName())
            );

            appList.setAdapter(notificationAdapter);

            swipeRefreshLayout = mainView.findViewById(R.id.swipe_refresh_layout);
            swipeRefreshLayout.setColorSchemeResources(R.color.orange, R.color.green, R.color.blue);
            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshNotificationsListener(mainView));
        }
    }
}
