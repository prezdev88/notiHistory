package org.prezdev.notihistory.listeners;

import android.graphics.Color;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.prezdev.notihistory.R;
import org.prezdev.notihistory.adapter.InstalledAppAdapter;
import org.prezdev.notihistory.model.InstalledApp;

public class OnInstalledAppClickListener implements AdapterView.OnItemClickListener {
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Switch chkAddApp = view.findViewById(R.id.chkAddApp);

        TextView lblAppInstalledName = view.findViewById(R.id.lblAppInstalledName);

        InstalledAppAdapter installedAppAdapter = (InstalledAppAdapter) adapterView.getAdapter();

        InstalledApp installedApp = (InstalledApp) installedAppAdapter.getItem(i);
        installedApp.toogle();

        chkAddApp.setChecked(installedApp.isSelected());

        //Toast.makeText(view.getContext(), installedApp.getId() + " - "+ lblAppInstalledName.getText(), Toast.LENGTH_LONG).show();
    }
}
