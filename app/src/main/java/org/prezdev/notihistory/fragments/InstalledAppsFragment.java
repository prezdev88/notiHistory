package org.prezdev.notihistory.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.prezdev.notihistory.MainActivity;
import org.prezdev.notihistory.R;
import org.prezdev.notihistory.adapter.InstalledAppAdapter;
import org.prezdev.notihistory.animations.Transition;
import org.prezdev.notihistory.cache.Cache;
import org.prezdev.notihistory.configuration.Preferences;
import org.prezdev.notihistory.listeners.OnInstalledAppClickListener;
import org.prezdev.notihistory.listeners.OnInstalledAppLongClickListener;
import org.prezdev.notihistory.listeners.OnInstalledAppStateChangeListener;
import org.prezdev.notihistory.listeners.swiperefresh.SwipeRefreshInstalledAppsListener;
import org.prezdev.notihistory.model.InstalledApp;
import org.prezdev.notihistory.service.AppService;
import org.prezdev.notihistory.service.impl.AppServiceImpl;

import java.util.List;

public class InstalledAppsFragment extends Fragment implements OnInstalledAppStateChangeListener {
    private ListView lvInstalledApps;
    private AppService appService;
    private Preferences preferences;
    private SwipeRefreshLayout appsSwipeRefresh;
    private List<InstalledApp> installedApps;

    public InstalledAppsFragment(){
        preferences = new Preferences();

        if(preferences.isFragmentTransition()){
            Transition.apply(this);
        }

        appService = new AppServiceImpl(MainActivity.getActivity());

        this.installedApps = Cache.getInstalledApps();
    }

    @Override
    public View onCreateView(
        LayoutInflater inflater,
        ViewGroup container,
        Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_installed_apps, container, false);
        Context context = view.getContext();

        lvInstalledApps = view.findViewById(R.id.lvInstalledApps);
        lvInstalledApps.setOnItemClickListener(new OnInstalledAppClickListener(this));
        lvInstalledApps.setOnItemLongClickListener(new OnInstalledAppLongClickListener());

        InstalledAppAdapter installedAppAdapter = new InstalledAppAdapter(context, installedApps, this);

        lvInstalledApps.setAdapter(installedAppAdapter);

        /*------------------------- Swipe Refresh -------------------------*/
        appsSwipeRefresh = view.findViewById(R.id.installedAppsSwipeRefresh);

        appsSwipeRefresh.setOnRefreshListener(new SwipeRefreshInstalledAppsListener(view));
        /*------------------------- Swipe Refresh -------------------------*/

        return view;
    }


    @Override
    public void stateChange(InstalledApp installedApp) {
        if(installedApp.isSelected()){
            appService.save(installedApp);
        }else{
            appService.delete(installedApp.getPackageName());
        }
    }
}
