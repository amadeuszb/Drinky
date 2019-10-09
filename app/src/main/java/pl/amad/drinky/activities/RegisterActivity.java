package pl.amad.drinky.activities;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import pl.amad.drinky.R;
import pl.amad.drinky.dao.UserDatabase;
import pl.amad.drinky.data.model.Party;

public class RegisterActivity extends AppCompatActivity {


    EditText login;
    EditText passwordOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        login = findViewById(R.id.username_registrate);
        passwordOne = findViewById(R.id.password_register);

        final Button registerButton = findViewById(R.id.register2);
        final ImageView backIcon = findViewById(R.id.backIcon);
       // backIcon.setOnClickListener((action) -> backToLoginForm());
        registerButton.setOnClickListener(i -> registerUser());
        NavUtils.getParentActivityIntent(this);
    }


    private void registerUser() {

        String passwordToAddUser = passwordOne.getText().toString();
        String loginToAddUser = login.getText().toString();
        UserDatabase db = UserDatabase.getInstance(this);
        Party nextParty = new Party();
        nextParty.setName(loginToAddUser);
        nextParty.setDescription(passwordToAddUser);
        if (db.dao().searchByUsername(loginToAddUser) == null) {
            db.dao().insert(nextParty);
        }

    }

    public void backToLoginForm(View view) {
        setContentView(R.layout.activity_login);
        finish();
    }
}
