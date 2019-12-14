package org.prezdev.notihistory.permission;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import org.prezdev.notihistory.MainActivity;

public class Permisions {
    public static void checkAppPermissions(){
        if (
            ContextCompat.checkSelfPermission(
                MainActivity.getActivity(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Permission is not granted
            ActivityCompat.requestPermissions(
                MainActivity.getActivity(),
                new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                },
                RequestCode.READ_AND_WRITE
            );

        }
    }
}
