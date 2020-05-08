package org.prezdev.notihistory.model.impl;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.prezdev.notihistory.configuration.Config;
import org.prezdev.notihistory.model.FileNotification;
import org.prezdev.notihistory.model.NotificationDao;
import org.prezdev.notihistory.model.NotificationVO;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileNotificationDaoImpl implements NotificationDao {

    private File notificationsFile;

    public FileNotificationDaoImpl(){
        this.notificationsFile = new File(Config.NOTIFICATIONS_FILE_PATH);
    }

    @Override
    public void save(NotificationVO notificationVO) {
        List<NotificationVO> notifications = findAll();
        notifications.add(notificationVO);

        save(notifications);
    }

    private void save(List<NotificationVO> notifications) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.writeValue(notificationsFile, notifications);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<NotificationVO> findAll() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            FileNotification fileNotification = objectMapper.readValue(
                notificationsFile,
                FileNotification.class
            );

            return fileNotification.getNotifications();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<NotificationVO> findAllByPackageName(String packageName) {
        return null;
    }

    @Override
    public List<NotificationVO> findAllByQuery(String query) {
        return null;
    }

    public void removeAll(){
        List<NotificationVO> notifications = findAll();
        notifications.clear();

        save(notifications);
    }

    public boolean hasNotifications(){
        return !findAll().isEmpty();
    }
}
