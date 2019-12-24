package org.prezdev.notihistory.listeners.bottomNavigation;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.MenuItem;

import org.prezdev.notihistory.MainActivity;
import org.prezdev.notihistory.R;
import org.prezdev.notihistory.configuration.preferences.SettingsActivity;
import org.prezdev.notihistory.fragments.AppsFragment;
import org.prezdev.notihistory.fragments.InstalledAppsFragment;
import org.prezdev.notihistory.service.FragmentService;
import org.prezdev.notihistory.service.impl.FragmentServiceImpl;

public class OnNavigationBottomClickListener implements BottomNavigationView.OnNavigationItemSelectedListener {

    private FragmentService fragmentService;
    private SearchView searchView;
    private MenuItem previousMenuItem;

    public OnNavigationBottomClickListener(SearchView searchView){
        this.fragmentService = new FragmentServiceImpl(MainActivity.getActivity());
        this.searchView = searchView;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        validateIconified();

        switch (menuItem.getItemId()){
            case R.id.navigationMyApps:
                fragmentService.load(new AppsFragment());
                break;

            case R.id.navigationInstalledApps:
                fragmentService.load(new InstalledAppsFragment());
                break;

            case R.id.navigationSearch:
                searchView.setIconified(false);
                break;
        }

        return true;
    }

    private void validateIconified() {
        if(!searchView.isIconified()){
            searchView.setIconified(true);
        }
    }
}
