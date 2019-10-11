package pl.amad.drinky.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.LinkedList;

import pl.amad.drinky.R;
import pl.amad.drinky.dao.PartyDatabase;
import pl.amad.drinky.data.model.Party;

public class PartyDescriptionFragment extends Fragment {


    TextView name;
    TextView description;
    DescriptionListener listener;
    View view;
    String nameText = "test";
    String descriptionText = "test";

    public interface DescriptionListener {
        void onInputPartyDescriptionSent(CharSequence input);
    }

    public PartyDescriptionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle savedInstanceState){
        view = layoutInflater.inflate(R.layout.fragment_party_description,viewGroup,false);
        name = view.findViewById(R.id.party_name_description);
        description= view.findViewById(R.id.party_description_description);
        return view;
    }

    public void updateTextView(CharSequence nameParty){
        PartyDatabase db = PartyDatabase.getInstance(getContext());
        Party party = db.dao().searchByName(nameParty.toString());

        descriptionText = party.getDescription();
        description.setText(descriptionText);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DescriptionListener) {
            listener = (DescriptionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement FragmentBListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

}
