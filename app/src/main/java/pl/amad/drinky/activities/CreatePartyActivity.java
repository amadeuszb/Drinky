package pl.amad.drinky.activities;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import pl.amad.drinky.R;
import pl.amad.drinky.dao.PartyDatabase;
import pl.amad.drinky.data.model.Party;

public class CreatePartyActivity extends AppCompatActivity {

    private EditText name;
    private EditText description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_party);
        name = findViewById(R.id.name_of_party_upade);
        description = findViewById(R.id.description_register_input);
        final Button registerButton = findViewById(R.id.register2);
        final ImageView backIcon = findViewById(R.id.backIcon);
        registerButton.setOnClickListener(i -> registerUser());
        NavUtils.getParentActivityIntent(this);
    }


    private void registerUser() {
        String descriptionToAdd = description.getText().toString();
        String nameToAdd = name.getText().toString();
        PartyDatabase db = PartyDatabase.getInstance(this);
        Party nextParty = new Party();
        nextParty.setName(nameToAdd);
        nextParty.setDescription(descriptionToAdd);
            if (db.dao().searchByName(nameToAdd) == null) {
            db.dao().insert(nextParty);
        }
        setContentView(R.layout.activity_list_of_parties);
        finish();

    }

    public void backToPartyActivityForm(View view) {
        setContentView(R.layout.activity_list_of_parties);
        finish();
    }
}
