package org.prezdev.notihistory.adapter;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorSpace;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.ColorInt;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.prezdev.notihistory.R;
import org.prezdev.notihistory.model.NotificationVO;
import org.prezdev.notihistory.model.Util;

import java.util.List;

public class NotificationAdapter extends BaseAdapter {

    private Context context;
    private List<NotificationVO> notifications;
    public Util util;

    public NotificationAdapter(Context context, List<NotificationVO> notifications) {
        this.context = context;
        this.notifications = notifications;
        this.util = Util.getInstance(context.getPackageManager());
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

        ImageView notiIcon = view.findViewById(R.id.notiIcon);

        TextView lblDatetime = view.findViewById(R.id.lblDatetime);
        TextView lblTitle = view.findViewById(R.id.lblTitle);
        TextView lblContent = view.findViewById(R.id.lblContent);

        String appName = util.getAppNameByPackageName(notification.getPackageName());

        lblDatetime.setText(appName+" - "+Util.getDateFormat(notification.getPostTime()));
        lblTitle.setText(notification.getExtraTitle());
        lblContent.setText((notification.getExtraBigText().isEmpty() ? notification.getExtraText() : notification.getExtraBigText()));

        try {
            notiIcon.setImageDrawable(util.getDrawableByPackageName(notification.getPackageName()));
            /*notiIcon.setImageDrawable(notification.getDrawable(view.getContext().getPackageManager()));

            ColorDrawable color = new ColorDrawable(notification.getColor());

            notiIcon.setColorFilter(color.getColor());*/
        } catch (PackageManager.NameNotFoundException e) {

        }

        return view;
    }
}
