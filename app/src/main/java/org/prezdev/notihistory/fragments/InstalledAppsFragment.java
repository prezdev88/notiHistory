package org.prezdev.notihistory.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.prezdev.notihistory.R;
import org.prezdev.notihistory.adapter.InstalledAppAdapter;
import org.prezdev.notihistory.configuration.Config;
import org.prezdev.notihistory.listeners.OnInstalledAppClickListener;
import org.prezdev.notihistory.listeners.OnInstalledAppLongClickListener;
import org.prezdev.notihistory.listeners.OnInstalledAppStateChangeListener;
import org.prezdev.notihistory.model.InstalledApp;
import org.prezdev.notihistory.permission.Permisions;
import org.prezdev.notihistory.service.AppService;
import org.prezdev.notihistory.service.impl.AppServiceImpl;

import java.util.ArrayList;
import java.util.List;


public class InstalledAppsFragment extends Fragment implements OnInstalledAppStateChangeListener {
    private ListView lvInstalledApps;
    private AppService appService;

    public InstalledAppsFragment(){
        if(Config.fragmentTransition){
            this.setExitTransition(new Fade());
            this.setEnterTransition(new Slide(Gravity.RIGHT).setDuration(300));
        }
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

        appService = new AppServiceImpl(context);

        List<InstalledApp> installedApps;
        try{
            installedApps = appService.getInstalledApps(Config.showSystemApps);
        }catch (Exception ex){
            installedApps = new ArrayList();

            Permisions.checkAppPermissions(getActivity());
        }

        InstalledAppAdapter installedAppAdapter = new InstalledAppAdapter(context, installedApps, this);

        lvInstalledApps.setAdapter(installedAppAdapter);

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
