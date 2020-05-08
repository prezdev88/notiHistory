package org.prezdev.notihistory.listeners.notificationListener;

import android.app.Notification;
import android.content.Context;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

import org.prezdev.notihistory.model.NotificationDao;
import org.prezdev.notihistory.model.NotificationVO;
import org.prezdev.notihistory.model.impl.FileNotificationDaoImpl;
import org.prezdev.notihistory.model.impl.SQLiteNotificationDaoImpl;
import org.prezdev.notihistory.service.impl.AppServiceImpl;

import java.util.Date;

/*https://www.javacodegeeks.com/2013/10/android-notificationlistenerservice-example.html*/
//if(!TextUtils.isEmpty(chars))

public class NotificationListenerServiceImpl extends NotificationListenerService {
    private NotificationDao notificationDao;
    private AppServiceImpl appService;

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        Context applicationContext = getApplicationContext();
        String packageName = sbn.getPackageName();
        appService = new AppServiceImpl(applicationContext);

        if(applicationContext != null){
            notificationDao = new SQLiteNotificationDaoImpl(applicationContext);
        }else{
            notificationDao = new FileNotificationDaoImpl();
        }

        // El usuario quiere guardar las notificaciones de esa app?
        if(appService.isAppInDatabase(packageName)){
            Notification notification   = sbn.getNotification();
            Bundle bundle               = notification.extras;
            CharSequence extraBigText   = bundle.getCharSequence(Notification.EXTRA_BIG_TEXT);
            int iconId                  = bundle.getInt(Notification.EXTRA_SMALL_ICON);

            NotificationVO notificationVO = new NotificationVO();

            notificationVO.setPackageName(packageName);
            notificationVO.setCategory(notification.category);
            notificationVO.setColor(notification.color);
            notificationVO.setIconId(iconId);

            notificationVO.setPostTime(new Date(sbn.getPostTime()));

            if(extraBigText != null){
                notificationVO.setExtraBigText(extraBigText.toString());
            }

            if(bundle.getCharSequence(Notification.EXTRA_SUMMARY_TEXT) != null){
                notificationVO.setExtraSummaryText(bundle.getCharSequence(Notification.EXTRA_SUMMARY_TEXT).toString());
            }

            if(bundle.getCharSequence(Notification.EXTRA_TEXT) != null){
                notificationVO.setExtraText(bundle.getCharSequence(Notification.EXTRA_TEXT).toString());
            }

            if(bundle.getCharSequence(Notification.EXTRA_TITLE) != null){
                notificationVO.setExtraTitle(bundle.getCharSequence(Notification.EXTRA_TITLE).toString());
            }

            notificationDao.save(notificationVO);
        }
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
