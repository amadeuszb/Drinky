package pl.amad.drinky.activities;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import pl.amad.drinky.R;
import pl.amad.drinky.dao.UserDatabase;
import pl.amad.drinky.data.model.User;

public class RegisterActivity extends AppCompatActivity {


    EditText login;
    EditText passwordOne ;
    EditText passwordTwo ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        login =  findViewById(R.id.username_registrate);
        passwordOne =  findViewById(R.id.password_register);
        passwordTwo =  findViewById(R.id.password_register_again);
        final Button registerButton = findViewById(R.id.register2);
        final ImageView backIcon = findViewById(R.id.backIcon);
        backIcon.setOnClickListener((action) -> backToLoginForm());
        registerButton.setOnClickListener(i -> registerUser());
        NavUtils.getParentActivityIntent(this);
    }

    protected void backToLoginForm(){
        setContentView(R.layout.activity_login);
        finish();
    }

    private void registerUser(){
    //TODO: Add user exist popup
        String passwordToAddUser = passwordOne.getText().toString();
        String loginToAddUser = login.getText().toString();
        if(passwordToAddUser.equals(passwordTwo.getText().toString())){
            UserDatabase db = UserDatabase.getInstance(this);
            User nextUser = new User();
            nextUser.setLogin(loginToAddUser);
            nextUser.setPassword(passwordToAddUser);
            if(db.dao().searchByUsername(loginToAddUser) == null) {
                db.dao().insert(nextUser);
            }
        }
    }
}
