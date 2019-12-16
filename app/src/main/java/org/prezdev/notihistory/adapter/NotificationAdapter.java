package org.prezdev.notihistory.adapter;

import android.content.Context;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.prezdev.notihistory.R;
import org.prezdev.notihistory.configuration.Config;
import org.prezdev.notihistory.configuration.Preferences;
import org.prezdev.notihistory.model.NotificationVO;
import org.prezdev.notihistory.model.Util;
import org.prezdev.notihistory.service.impl.AppServiceImpl;

import java.util.List;

public class NotificationAdapter extends BaseAdapter {

    private Context context;
    private List<NotificationVO> notifications;
    private AppServiceImpl appService;
    private Preferences preferences;

    public NotificationAdapter(Context context, List<NotificationVO> notifications) {
        this.context = context;
        this.notifications = notifications;
        this.appService = new AppServiceImpl(context);
        this.preferences = new Preferences();
    }

    @Override
    public int getCount() {
        return notifications.size();
    }

    @Override
    public Object getItem(int i) {
        return notifications.get(i);
    }

    @Override
    public long getItemId(int i) {
        return notifications.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.activity_notification_item, null);
        }

        NotificationVO notification = notifications.get(i);

        ImageView notiIcon = view.findViewById(R.id.appIcon);

        TextView lblDatetime = view.findViewById(R.id.lblAppName);
        TextView lblTitle = view.findViewById(R.id.lblTitle);
        TextView lblContent = view.findViewById(R.id.lblContent);

        String appName = appService.getAppNameByPackageName(notification.getPackageName());

        lblDatetime.setText(appName+" - "+Util.getDateFormat(notification.getPostTime()));
        lblTitle.setText(notification.getExtraTitle());
        lblContent.setText((notification.getExtraBigText().isEmpty() ? notification.getExtraText() : notification.getExtraBigText()));

        try {
            notiIcon.setImageDrawable(appService.getDrawableByPackageName(notification.getPackageName()));
            /*notiIcon.setImageDrawable(notification.getDrawable(view.getContext().getPackageManager()));

            ColorDrawable color = new ColorDrawable(notification.getColor());

            notiIcon.setColorFilter(color.getColor());*/
        } catch (PackageManager.NameNotFoundException e) {

        }

        if(preferences.isAppItemListAnimation()){
            view.setAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_in_left));
        }

        return view;
    }
}
