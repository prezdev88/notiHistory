package org.prezdev.notihistory.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.prezdev.notihistory.R;
import org.prezdev.notihistory.adapter.NotificationAdapter;
import org.prezdev.notihistory.configuration.Preferences;
import org.prezdev.notihistory.listeners.swiperefresh.SwipeRefreshNotificationsListener;
import org.prezdev.notihistory.model.NotificationVO;
import org.prezdev.notihistory.model.Util;
import org.prezdev.notihistory.service.impl.NotificationServiceImpl;

import java.util.List;

public class NotificationsFragment extends Fragment {

    private NotificationServiceImpl notificationService;
    private ListView lvNotifications;
    private SwipeRefreshLayout notificationsSwipeRefresh;
    private Preferences preferences;

    public NotificationsFragment(){
        preferences = new Preferences();

        if(preferences.isFragmentTransition()){
            this.setExitTransition(new Fade());
            this.setEnterTransition(new Slide(Gravity.RIGHT).setDuration(300));
        }
    }

    @Override
    public View onCreateView(
        LayoutInflater inflater,
        ViewGroup container,
        Bundle savedInstanceState
    ) {

        View view = inflater.inflate(R.layout.fragment_notifications, container, false);

        /*------------------------- Swipe Refresh -------------------------*/
        notificationsSwipeRefresh = view.findViewById(R.id.notificationsSwipeRefresh);

        notificationsSwipeRefresh.setColorSchemeResources(
            R.color.orange,
            R.color.green,
            R.color.blue
        );

        notificationsSwipeRefresh.setOnRefreshListener(new SwipeRefreshNotificationsListener(view));
        /*------------------------- Swipe Refresh -------------------------*/

        notificationService = new NotificationServiceImpl(view.getContext());
        lvNotifications = view.findViewById(R.id.lvNotifications);

        // Se obtiene el nombre del paquete de la app seleccionada por el usuario
        String packageName = Util.currentNotificationApp.getPackageName();

        // Se obtienen las notificaciones de esa app
        List<NotificationVO> notifications = notificationService.findAllByPackageName(packageName);

        // Se crea el adapter
        NotificationAdapter notificationAdapter = new NotificationAdapter(
            view.getContext(),
            notifications
        );

        // Se colocan las notificaciones de esa app en el list view
        lvNotifications.setAdapter(notificationAdapter);

        return view;
    }

}
