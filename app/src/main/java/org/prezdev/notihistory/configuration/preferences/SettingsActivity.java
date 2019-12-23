package org.prezdev.notihistory.configuration.preferences;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.view.ViewGroup;

import org.prezdev.notihistory.R;

// https://www.programcreek.com/java-api-examples/?class=android.support.v7.app.AppCompatActivity&method=getSupportActionBar
public class SettingsActivity extends AppCompatPreferenceActivity {
    private static final String TAG = SettingsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //setupActionBar();

        // load settings fragment
        getFragmentManager()
                .beginTransaction()
                .replace(
                    android.R.id.content,
                    new MainPreferenceFragment()
                ).commit();
    }
    private void setupActionBar() {
        ViewGroup rootView = (ViewGroup)findViewById(R.id.action_bar_root); //id from appcompat

        if (rootView != null) {
            View view = getLayoutInflater().inflate(R.layout.app_bar_main, rootView, false);
            rootView.addView(view, 0);

            android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
        }

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
