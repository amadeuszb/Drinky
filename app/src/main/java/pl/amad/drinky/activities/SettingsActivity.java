package pl.amad.drinky.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import pl.amad.drinky.R;
import java.util.Locale;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

public class SettingsActivity extends AppCompatActivity {

    Button languageButton;
    SharedPreferences sharedPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        sharedPref = getSharedPreferences("lang", Context.MODE_PRIVATE);
        languageButton = findViewById(R.id.language_button);
        languageButton.setText(sharedPref.getString("language","en"));
        languageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLang();
            }
        });
    }

    public void setLang(){
        if(sharedPref.getString("language","en").equals("pl")) {
            languageButton.setText("en");
            setLocale("en");
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("language", "en");
            editor.commit();
        }
        else {
            languageButton.setText("pl");
            setLocale("pl");
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("language", "pl");
            editor.commit();
        }
    }

    public void setLocale(String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        //Intent refresh = new Intent(this, SettingsActivity.class);
       // finish();
       // startActivity(refresh);
    }
}
