package org.prezdev.notihistory.listeners.swiperefresh;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import org.prezdev.notihistory.MainActivity;
import org.prezdev.notihistory.R;
import org.prezdev.notihistory.fragments.InstalledAppsFragment;
import org.prezdev.notihistory.service.impl.FragmentServiceImpl;

public class SwipeRefreshInstalledAppsListener extends SwipeRefreshSettings implements SwipeRefreshLayout.OnRefreshListener, Runnable {

    private SwipeRefreshLayout appsSwipeRefresh;
    private FragmentServiceImpl fragmentService;

    public SwipeRefreshInstalledAppsListener(View view) {
        this.appsSwipeRefresh = view.findViewById(R.id.installedAppsSwipeRefresh);
        this.fragmentService = new FragmentServiceImpl(MainActivity.getActivity());
        super.applySettings(this.appsSwipeRefresh);
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
