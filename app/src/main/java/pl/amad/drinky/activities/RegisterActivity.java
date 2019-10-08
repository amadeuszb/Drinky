package pl.amad.drinky.activities;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import pl.amad.drinky.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final Button registerButton = findViewById(R.id.register2);
        final ImageView backIcon = findViewById(R.id.backIcon);
        backIcon.setOnClickListener((action) -> backToLoginForm());
        NavUtils.getParentActivityIntent(this);
    }

    protected void backToLoginForm(){
        setContentView(R.layout.activity_login);
        finish();
    }
}
