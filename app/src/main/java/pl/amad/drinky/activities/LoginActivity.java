package pl.amad.drinky.activities;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import pl.amad.drinky.R;
import pl.amad.drinky.activities.RegisterActivity;
import pl.amad.drinky.data.model.User;

public class LoginActivity extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        final Button registerButton = findViewById(R.id.register);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);
        registerButton.setActivated(true);
        loginButton.setOnClickListener((action) -> {
            onLoginClick(usernameEditText, passwordEditText);
        });
        registerButton.setOnClickListener((action) -> switchToRegister());


    }

    private void switchToRegister() {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    private void onLoginClick(EditText username, EditText password) {
        //TODO
        User user = new User(username.getText().toString(), password.getText().toString());
        if (user.getLogin().isEmpty()) {
            startActivity(new Intent(this, ListOfDrinksActivity.class));
            finish();
        } else {

        }
    }


}
