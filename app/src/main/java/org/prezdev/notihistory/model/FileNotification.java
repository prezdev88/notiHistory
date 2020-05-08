package org.prezdev.notihistory.model;

import java.util.List;

public class FileNotification {
    private List<NotificationVO> notifications;

    public List<NotificationVO> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<NotificationVO> notifications) {
        this.notifications = notifications;
    }
}
