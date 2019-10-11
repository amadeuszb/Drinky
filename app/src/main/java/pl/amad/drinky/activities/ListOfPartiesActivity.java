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
import pl.amad.drinky.fragments.PartyDescriptionFragment;
import pl.amad.drinky.fragments.PartyListFragment;

public class ListOfPartiesActivity extends AppCompatActivity implements PartyListFragment.PartyListListener, PartyDescriptionFragment.DescriptionListener  {

    private RecyclerView.LayoutManager layoutManager;

    private Button goToCreatePartyButton;
    private LinkedList<Party> partiesList;
    private PartyDescriptionFragment partyDescriptionFragment;
    private PartyListFragment partyListFragment;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_parties);
        goToCreatePartyButton = findViewById(R.id.register);
        partiesList = new LinkedList<>();
        layoutManager = new LinearLayoutManager(this);
        partyDescriptionFragment = new PartyDescriptionFragment();
        partyListFragment = new PartyListFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_list, partyListFragment)
                .replace(R.id.fragment_description, partyDescriptionFragment)
                .commit();
        goToCreatePartyButton.setActivated(true);

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



    public void backToMenuForm(View view) {
        setContentView(R.layout.activity_menu);
        finish();
    }

    @Override
    public void onInputPartyDescriptionSent(CharSequence input) {
        partyDescriptionFragment.updateTextView(input);

        getSupportFragmentManager().beginTransaction()
                  .replace(R.id.fragment_list, partyListFragment)
                .replace(R.id.fragment_description, partyDescriptionFragment)
                .commit();
    }

    @Override
    public void onInputPartyListSent(CharSequence input) {
        partyDescriptionFragment.updateTextView(input);

        getSupportFragmentManager().beginTransaction()
               .replace(R.id.fragment_list, partyListFragment)
                .replace(R.id.fragment_description, partyDescriptionFragment)
                .commit();
    }
}
