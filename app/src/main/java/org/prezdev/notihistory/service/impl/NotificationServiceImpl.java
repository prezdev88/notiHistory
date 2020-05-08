package org.prezdev.notihistory.service.impl;

import android.content.Context;
import android.widget.Toast;

import org.prezdev.notihistory.MainActivity;
import org.prezdev.notihistory.model.NotificationVO;
import org.prezdev.notihistory.model.impl.FileNotificationDaoImpl;
import org.prezdev.notihistory.model.impl.SQLiteNotificationDaoImpl;
import org.prezdev.notihistory.service.NotificationService;

import java.util.List;

public class NotificationServiceImpl implements NotificationService {

    private SQLiteNotificationDaoImpl sqLiteNotificationDao;

    public NotificationServiceImpl(Context context){
        sqLiteNotificationDao = new SQLiteNotificationDaoImpl(context);
    }

    @Override
    public List<NotificationVO> read() {
        return sqLiteNotificationDao.findAll();
    }

    @Override
    public List<NotificationVO> findAllByPackageName(String packageName) {
        return sqLiteNotificationDao.findAllByPackageName(packageName);
    }

    @Override
    public void loadFileNotifications() {
        FileNotificationDaoImpl fileNotificationDao = new FileNotificationDaoImpl();

        if(fileNotificationDao.hasNotifications()){
            List<NotificationVO> notifications = fileNotificationDao.findAll();

            for(NotificationVO notificationVO : notifications){
                sqLiteNotificationDao.save(notificationVO);
            }

            Toast.makeText(MainActivity.getActivity(), notifications.size() + " notificaciones creadas!", Toast.LENGTH_LONG).show();

            fileNotificationDao.removeAll();
        }
    }
}
