package org.prezdev.notihistory.fragments.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import org.prezdev.notihistory.R;
import org.prezdev.notihistory.configuration.Config;

public class NotificationConfigDialog extends DialogFragment {

    public NotificationConfigDialog(){
        Config.notificatioConfigDialogIsVisible = true;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage(R.string.notificationNotConfigured)
            .setPositiveButton(R.string._continue, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    Config.notificatioConfigDialogIsVisible = false;
                    Intent intent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
                    startActivity(intent);
                }
            })
            .setNegativeButton(R.string.exit, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    Config.notificatioConfigDialogIsVisible = false;
                    Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                    homeIntent.addCategory( Intent.CATEGORY_HOME );
                    homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(homeIntent);
                }
            });

        return builder.create();
    }

}
