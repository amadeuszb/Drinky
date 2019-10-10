package pl.amad.drinky.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.LinkedList;

import pl.amad.drinky.R;
import pl.amad.drinky.adapters.ListElementPartyAdapter;
import pl.amad.drinky.dao.PartyDatabase;
import pl.amad.drinky.data.model.Party;

public class ListOfPartiesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Button selectPartyButton;
    private Button goToCreatePartyButton;
    private LinkedList<Party> partiesList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_parties);
        recyclerView = findViewById(R.id.name_list);
        recyclerView.setHasFixedSize(true);

        selectPartyButton = findViewById(R.id.name);
        goToCreatePartyButton = findViewById(R.id.register);
        partiesList = new LinkedList<>();
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        recyclerView.setAdapter(new ListElementPartyAdapter(onSelectClick(),recyclerView,this));

        goToCreatePartyButton.setActivated(true);
        selectPartyButton.setOnClickListener((action) -> {
            //onSelectClick(usernameEditText, passwordEditText);
        });
        goToCreatePartyButton.setOnClickListener((action) -> switchToCreatePartyActivity());


    }

    private void switchToCreatePartyActivity() {
        startActivity(new Intent(this, CreatePartyActivity.class));
    }

    private LinkedList<Party> onSelectClick() {
        PartyDatabase db = PartyDatabase.getInstance(this);
        LinkedList<Party> allParties = new LinkedList<>();

        allParties.addAll(db.dao().getAllParties());
        return allParties;
    }


    protected void backToLoginForm() {
        setContentView(R.layout.activity_menu);
        finish();
    }

    public void backToMenuForm(View view) {
        setContentView(R.layout.activity_menu);
        finish();
    }
}
