package pl.amad.drinky.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import pl.amad.drinky.R;
import pl.amad.drinky.dao.UserDatabase;
import pl.amad.drinky.data.model.Party;

public class LoginActivity extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.description);
        final Button loginButton = findViewById(R.id.name);
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
        UserDatabase db = UserDatabase.getInstance(this);
        Party party = db.dao().searchByUsername(username.getText().toString());
        if (party != null && party.getDescription().equals(password.getText().toString())) {
            startActivity(new Intent(this, ListOfDrinksActivity.class));
            finish();
        } else {

        }
    }


}
