package pl.amad.drinky.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import pl.amad.drinky.R;

public class MenuActivity extends AppCompatActivity {
//TODO Change Methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Bundle b = getIntent().getExtras();
        int id = 0;
        if (b != null) {
            id = b.getInt("id");
        }
        System.out.println(id);
    }


    public void goToParties(View view) {
        startActivity(new Intent(this, ListOfPartiesActivity.class));

    }

    public void goToList(View view) {
        startActivity(new Intent(this, ListOfDrinksActivity.class));

    }
}
