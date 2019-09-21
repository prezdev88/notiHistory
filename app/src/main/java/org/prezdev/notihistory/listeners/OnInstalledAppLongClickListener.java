package org.prezdev.notihistory.listeners;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import org.prezdev.notihistory.R;

public class OnInstalledAppLongClickListener implements AdapterView.OnItemLongClickListener {

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        TextView lblAppPackageName = view.findViewById(R.id.lblAppPackageName);

        PackageManager packageManager = view.getContext().getPackageManager();
        Intent intent = packageManager.getLaunchIntentForPackage(lblAppPackageName.getText().toString());

        view.getContext().startActivity(intent);

        return false;
    }
}
