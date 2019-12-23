package org.prezdev.notihistory.listeners.swiperefresh;

import android.support.v4.widget.SwipeRefreshLayout;

import org.prezdev.notihistory.MainActivity;
import org.prezdev.notihistory.R;

public class SwipeRefreshSettings {

    public void applySettings(SwipeRefreshLayout swipeRefreshLayout){
        applyColor(swipeRefreshLayout);
    }

    private void applyColor(SwipeRefreshLayout swipeRefreshLayout){

        swipeRefreshLayout.setColorSchemeResources(
            R.color.red,
            R.color.green,
            R.color.blue
        );
    }
}
