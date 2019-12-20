package org.prezdev.notihistory.listeners.floaticon;

import android.support.v7.widget.SearchView;
import android.view.View;

public class OnFloatIconListener implements View.OnClickListener {

    private SearchView searchView;

    public OnFloatIconListener(SearchView searchView){
        this.searchView = searchView;
    }

    @Override
    public void onClick(View view) {
        searchView.setIconified(false);
    }
}
