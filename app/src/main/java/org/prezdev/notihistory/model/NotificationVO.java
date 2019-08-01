package org.prezdev.notihistory.model;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;

import com.google.gson.GsonBuilder;

import org.prezdev.notihistory.MainActivity;

import java.util.Date;

public class NotificationVO {
    private int id;
    private int color;
    private String category;
    private Date postTime;
    private String packageName;

    private int iconId;
    private String extraText;
    private String extraTitle;
    private String extraSummaryText;
    private String extraBigText;

    public Drawable getDrawable(PackageManager packageManager) throws PackageManager.NameNotFoundException {
        Resources resources = packageManager.getResourcesForApplication(packageName);

        Drawable icon = resources.getDrawable(iconId);

        return icon;
    }

    public String getName(PackageManager packageManager) {
        ApplicationInfo applicationInfo;
        try {
            applicationInfo = packageManager.getApplicationInfo( this.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            applicationInfo = null;
        }

        return (String) (applicationInfo != null ? packageManager.getApplicationLabel(applicationInfo) : "App sin nombre");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getCategory() {
        return (category == null ? "" : category);
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public String getExtraText() {
        return (extraText == null ? "":extraText);
    }

    public void setExtraText(String extraText) {
        this.extraText = extraText;
    }

    public String getExtraTitle() {
        return (extraTitle == null ?"":extraTitle);
    }

    public void setExtraTitle(String extraTitle) {
        this.extraTitle = extraTitle;
    }

    public String getExtraSummaryText() {
        return (extraSummaryText == null ? "" : extraSummaryText);
    }

    public void setExtraSummaryText(String extraSummaryText) {
        this.extraSummaryText = extraSummaryText;
    }

    public String getExtraBigText() {
        return (extraBigText == null ? "" : extraBigText);
    }

    public void setExtraBigText(String extraBigText) {
        this.extraBigText = extraBigText;
    }

    @Override
    public String toString() {
        return new GsonBuilder().create().toJson(this, NotificationVO.class);
    }
}
