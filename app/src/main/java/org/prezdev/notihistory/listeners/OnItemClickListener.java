package org.prezdev.notihistory.listeners;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.prezdev.notihistory.adapter.AppAdapter;
import org.prezdev.notihistory.adapter.NotificationAdapter;
import org.prezdev.notihistory.model.App;
import org.prezdev.notihistory.service.impl.NotificationServiceImpl;

public class OnItemClickListener implements AdapterView.OnItemClickListener {

    private NotificationServiceImpl notificationService;
    private NotificationAdapter notificationAdapter;
    private ListView appList;

    public OnItemClickListener(ListView appList) {
        this.appList = appList;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        notificationService = new NotificationServiceImpl(view.getContext());

        if(adapterView.getAdapter() instanceof AppAdapter){
            AppAdapter appAdapter = (AppAdapter) adapterView.getAdapter();

            App app = (App) appAdapter.getItem(i);

            notificationAdapter = new NotificationAdapter(
                view.getContext(),
                notificationService.findAllByPackageName(app.getPackageName())
            );

            appList.setAdapter(notificationAdapter);
        }
    }
}
