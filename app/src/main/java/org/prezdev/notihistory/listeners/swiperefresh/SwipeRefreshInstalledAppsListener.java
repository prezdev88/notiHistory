package org.prezdev.notihistory.listeners.swiperefresh;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ListView;

import org.prezdev.notihistory.MainActivity;
import org.prezdev.notihistory.R;
import org.prezdev.notihistory.adapter.AppAdapter;
import org.prezdev.notihistory.adapter.InstalledAppAdapter;
import org.prezdev.notihistory.fragments.InstalledAppsFragment;
import org.prezdev.notihistory.model.InstalledApp;
import org.prezdev.notihistory.model.NotificationInstalledApp;
import org.prezdev.notihistory.service.AppService;
import org.prezdev.notihistory.service.impl.AppServiceImpl;
import org.prezdev.notihistory.service.impl.FragmentServiceImpl;

import java.util.List;

public class SwipeRefreshInstalledAppsListener implements SwipeRefreshLayout.OnRefreshListener, Runnable {

    private SwipeRefreshLayout appsSwipeRefresh;
    private FragmentServiceImpl fragmentService;

    public SwipeRefreshInstalledAppsListener(View view) {
        this.appsSwipeRefresh = view.findViewById(R.id.installedAppsSwipeRefresh);
        this.fragmentService = new FragmentServiceImpl(MainActivity.getActivity());
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(this, 500);
    }

    @Override
    public void run() {
        fragmentService.load(new InstalledAppsFragment());

        // Parando la animación de load
        appsSwipeRefresh.setRefreshing(false);
    }
}
