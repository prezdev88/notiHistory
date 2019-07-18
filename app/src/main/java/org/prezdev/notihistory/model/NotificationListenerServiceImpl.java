package org.prezdev.notihistory.model;

import android.app.Notification;
import android.content.Intent;
import android.os.Build;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.Log;

import java.util.Date;

public class NotificationListenerServiceImpl extends NotificationListenerService {
    private String tag = this.getClass().getSimpleName();

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        Notification notification = sbn.getNotification();
        /*https://www.javacodegeeks.com/2013/10/android-notificationlistenerservice-example.html*/
        Log.i(tag, "********** Notificación NUEVA **********");
        Log.i(tag, "ID :" + sbn.getId());
        Log.i(tag, "Ticket text: "+ notification.tickerText);
        Log.i(tag, "Category: "+ notification.category);
        Log.i(tag, "Color: "+ notification.color);
        Log.i(tag, "Icon Level: "+ notification.iconLevel);
        Log.i(tag, "Group: "+notification.getGroup());
        Log.i(tag, "Large Icon: "+notification.getLargeIcon());
        Log.i(tag, "Small Icon: "+notification.getSmallIcon());
        Log.i(tag, "Sort key: "+notification.getSortKey());
        Log.i(tag, "Post time: "+new Date(sbn.getPostTime()));
        Log.i(tag, "User: "+sbn.getUser());
        Log.i(tag, "EXTRA_TEXT: "+notification.extras.getCharSequence(Notification.EXTRA_TEXT));
        Log.i(tag, "EXTRA_TITLE: "+notification.extras.getCharSequence(Notification.EXTRA_TITLE));
        Log.i(tag, "EXTRA_SUB_TEXT: "+notification.extras.getCharSequence(Notification.EXTRA_SUB_TEXT));
        Log.i(tag, "EXTRA_SUMMARY_TEXT: "+notification.extras.getCharSequence(Notification.EXTRA_SUMMARY_TEXT));
        Log.i(tag, "EXTRA_INFO_TEXT: "+notification.extras.getCharSequence(Notification.EXTRA_INFO_TEXT));

        try{
            CharSequence[] lines =
                    notification.extras.getCharSequenceArray(Notification.EXTRA_TEXT_LINES);
            if(lines != null && lines.length > 0) {
                StringBuilder sb = new StringBuilder();
                for (CharSequence msg : lines) {
                    if (!TextUtils.isEmpty(msg)) {
                        sb.append(msg.toString());
                        sb.append('\n');
                    }
                }
                Log.i(tag, "EXTRA_TEXT_LINES: "+sb.toString().trim());
            }
            CharSequence chars =
                    notification.extras.getCharSequence(Notification.EXTRA_BIG_TEXT);
            if(!TextUtils.isEmpty(chars))
                Log.i(tag, "EXTRA_BIG_TEXT: "+chars.toString());


        }catch(Exception ex){
            Log.i(tag, "cago: "+ex.getMessage());
        }
        Log.i(tag,"****************************************");
        /*Intent i = new  Intent("com.kpbird.nlsexample.NOTIFICATION_LISTENER_EXAMPLE");
        i.putExtra("notification_event","onNotificationPosted :" + sbn.getPackageName() + "n");
        sendBroadcast(i);*/

    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        Log.i(tag,"********** onNOtificationRemoved");
        Log.i(tag,"ID :" + sbn.getId() + "t" + sbn.getNotification().tickerText +"t" + sbn.getPackageName());
        Intent i = new  Intent("com.kpbird.nlsexample.NOTIFICATION_LISTENER_EXAMPLE");
        i.putExtra("notification_event","onNotificationRemoved :" + sbn.getPackageName() + "n");

        sendBroadcast(i);
    }
}
