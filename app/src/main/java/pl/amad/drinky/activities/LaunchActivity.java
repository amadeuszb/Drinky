package pl.amad.drinky.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import java.util.Locale;

import pl.amad.drinky.R;

public class LaunchActivity extends AppCompatActivity {
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = getSharedPreferences("lang", Context.MODE_PRIVATE);
        setLocale(sharedPref.getString("language","en"));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch_layout);

    }
    public void setLocale(String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);

    }
    @Override
    protected void onResume() {
        super.onResume();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    startApplication();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }

    private void startApplication() {
        startActivity(new Intent(this, MenuActivity.class));
        finish();
    }
}