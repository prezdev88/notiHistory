package org.prezdev.notihistory.fragments;

import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.prezdev.notihistory.R;
import org.prezdev.notihistory.adapter.AppAdapter;
import org.prezdev.notihistory.animations.Transition;
import org.prezdev.notihistory.configuration.Preferences;
import org.prezdev.notihistory.listeners.OnAppClickListener;
import org.prezdev.notihistory.listeners.swiperefresh.SwipeRefreshAppsListener;
import org.prezdev.notihistory.model.NotificationInstalledApp;
import org.prezdev.notihistory.permission.Permisions;
import org.prezdev.notihistory.service.impl.AppServiceImpl;

import java.util.ArrayList;
import java.util.List;

/*This fragment is where the user see that notifications apps*/
public class AppsFragment extends Fragment {

    private SwipeRefreshLayout appsSwipeRefresh;
    private AppServiceImpl appService;
    private List<NotificationInstalledApp> notificationApps;
    private AppAdapter appAdapter;
    private ListView lvApps;
    private Preferences preferences;

    public AppsFragment() {
        preferences = new Preferences();

        if(preferences.isFragmentTransition()){
            Transition.apply(this);
        }
    }

    @Override
    public View onCreateView(
        LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_apps, container, false);

        lvApps = view.findViewById(R.id.lvApps);

        lvApps.setOnItemClickListener(new OnAppClickListener(getActivity()));

        appService = new AppServiceImpl(view.getContext());

        try {
            notificationApps = appService.getNotificationInstalledApps();
        }catch (SQLiteCantOpenDatabaseException ex){
            notificationApps = new ArrayList<>();

            Permisions.checkAppPermissions(getActivity());
        }

        appAdapter = new AppAdapter(view.getContext(), notificationApps);

        lvApps.setAdapter(appAdapter);

        /*------------------------- Swipe Refresh -------------------------*/
        appsSwipeRefresh = view.findViewById(R.id.appsSwipeRefresh);

        appsSwipeRefresh.setColorSchemeResources(
            R.color.orange,
            R.color.green,
            R.color.blue
        );

        appsSwipeRefresh.setOnRefreshListener(new SwipeRefreshAppsListener(view));
        /*------------------------- Swipe Refresh -------------------------*/

        return view;
    }




}
