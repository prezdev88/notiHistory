package org.prezdev.notihistory.listeners;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Switch;

import org.prezdev.notihistory.R;
import org.prezdev.notihistory.adapter.InstalledAppAdapter;
import org.prezdev.notihistory.model.InstalledApp;

public class OnInstalledAppClickListener implements AdapterView.OnItemClickListener {

    private OnInstalledAppStateChangeListener onInstalledAppStateChangeListener;

    public OnInstalledAppClickListener(OnInstalledAppStateChangeListener onInstalledAppStateChangeListener) {
        this.onInstalledAppStateChangeListener = onInstalledAppStateChangeListener;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Switch chkAddApp = view.findViewById(R.id.chkAddApp);

        InstalledAppAdapter installedAppAdapter = (InstalledAppAdapter) adapterView.getAdapter();

        InstalledApp installedApp = (InstalledApp) installedAppAdapter.getItem(i);
        installedApp.toogle();

        chkAddApp.setChecked(installedApp.isSelected());

        onInstalledAppStateChangeListener.stateChange(installedApp);
    }
}
