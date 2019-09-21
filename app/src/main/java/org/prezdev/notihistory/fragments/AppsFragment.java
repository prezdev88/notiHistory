package org.prezdev.notihistory.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.prezdev.notihistory.MainActivity;
import org.prezdev.notihistory.R;
import org.prezdev.notihistory.adapter.AppAdapter;
import org.prezdev.notihistory.listeners.OnAppClickListener;
import org.prezdev.notihistory.listeners.SwipeRefreshAppsListener;
import org.prezdev.notihistory.model.NotificationInstalledApp;
import org.prezdev.notihistory.service.impl.NotificationServiceImpl;

import java.util.List;

@SuppressLint("ValidFragment")
public class AppsFragment extends Fragment {

    private SwipeRefreshLayout appsSwipeRefresh;
    private NotificationServiceImpl notificationService;
    private List<NotificationInstalledApp> notificationApps;
    private AppAdapter appAdapter;
    private ListView lvApps;
    private MainActivity mainActivity;

    public AppsFragment(){}

    public AppsFragment(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        //this.setExitTransition(new Slide(Gravity.LEFT).setDuration(300));
        this.setExitTransition(new Fade());
        this.setEnterTransition(new Slide(Gravity.LEFT).setDuration(300));
    }

    @Override
    public View onCreateView(
        LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_apps, container, false);

        lvApps = view.findViewById(R.id.lvApps);

        lvApps.setOnItemClickListener(new OnAppClickListener(this.mainActivity));

        notificationService = NotificationServiceImpl.getInstance(view.getContext());
        notificationApps = notificationService.getNotificationApps();

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
