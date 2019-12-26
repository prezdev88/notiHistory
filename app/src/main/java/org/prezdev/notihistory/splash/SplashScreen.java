package org.prezdev.notihistory.splash;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.prezdev.notihistory.MainActivity;
import org.prezdev.notihistory.configuration.Preferences;
import org.prezdev.notihistory.welcome.WelcomeActivity;

public class SplashScreen extends AppCompatActivity {
    private Preferences preferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = new Preferences(this);

        Intent intent;

        if(preferences.isFirstTimeLaunch()){
            intent = new Intent(this, WelcomeActivity.class);
        }else{
            intent = new Intent(this, MainActivity.class);
        }

        startActivity(intent);
        finish();
    }
}
