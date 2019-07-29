package org.prezdev.notihistory.notificationListener;

import android.app.Notification;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import org.prezdev.notihistory.model.NotificationVO;
import org.prezdev.notihistory.model.impl.NotificationDao;

import java.util.Date;

/*https://www.javacodegeeks.com/2013/10/android-notificationlistenerservice-example.html*/
//if(!TextUtils.isEmpty(chars))

public class NotificationListenerServiceImpl extends NotificationListenerService {
    private NotificationDao notificationDao;

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        notificationDao = new NotificationDao(getApplicationContext());

        Notification notification = sbn.getNotification();
        Bundle bundle = notification.extras;
        CharSequence extraBigText = bundle.getCharSequence(Notification.EXTRA_BIG_TEXT);
        int iconId = bundle.getInt(Notification.EXTRA_SMALL_ICON);
        String packageName = sbn.getPackageName();

        NotificationVO noti = new NotificationVO();

        noti.setPackageName(packageName);
        noti.setCategory(notification.category);
        noti.setColor(notification.color);
        noti.setIconId(iconId);

        noti.setPostTime(new Date(sbn.getPostTime()));

        if(extraBigText != null){
            noti.setExtraBigText(extraBigText.toString());
        }

        if(bundle.getCharSequence(Notification.EXTRA_SUMMARY_TEXT) != null){
            noti.setExtraSummaryText(bundle.getCharSequence(Notification.EXTRA_SUMMARY_TEXT).toString());
        }

        if(bundle.getCharSequence(Notification.EXTRA_TEXT) != null){
            noti.setExtraText(bundle.getCharSequence(Notification.EXTRA_TEXT).toString());
        }

        if(bundle.getCharSequence(Notification.EXTRA_TITLE) != null){
            noti.setExtraTitle(bundle.getCharSequence(Notification.EXTRA_TITLE).toString());
        }

        Log.d("New Notification", noti.toString());

        notificationDao.save(noti);
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        /*Log.i(tag,"********** onNOtificationRemoved");
        Log.i(tag,"ID :" + sbn.getId() + "t" + sbn.getNotification().tickerText +"t" + sbn.getPackageName());
        Intent i = new  Intent("com.kpbird.nlsexample.NOTIFICATION_LISTENER_EXAMPLE");
        i.putExtra("notification_event","onNotificationRemoved :" + sbn.getPackageName() + "n");

        sendBroadcast(i);*/
    }
}
