package pl.amad.drinky.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import pl.amad.drinky.R;
import pl.amad.drinky.dao.PartyDatabase;
import pl.amad.drinky.data.model.Party;

public class PartyDescriptionFragment extends Fragment {


    TextView name;
    TextView description;
    Button shareButton;
    Button deleteButton;
    DescriptionListener listener;
    View view;
    String nameText = "";
    String descriptionText = "";

    public PartyDescriptionFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        view = layoutInflater.inflate(R.layout.fragment_party_description, viewGroup, false);
        name = view.findViewById(R.id.party_name_description_text);
        description = view.findViewById(R.id.party_description_description);
        name.setText(nameText);
        description.setText(descriptionText);
        shareButton = view.findViewById(R.id.button_facebook_share);
        deleteButton = view.findViewById(R.id.button_delete_party);

        shareButton.setActivated(false);
        return view;
    }

    public View.OnClickListener onButtonShare(String textToShare) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, textToShare);
        intent.setType("text/plain");
        return v -> startActivity(Intent.createChooser(intent, getString(R.string.share)));
    }

    public View.OnClickListener onButtonDelete() {
        return v -> {
            delete();
            listener.onInputPartyDescriptionSent("");
        };
    }
    private void delete() {
        String nameToDelete = name.getText().toString();
        PartyDatabase db = PartyDatabase.getInstance(getContext());
        if (db.dao().searchByName(nameToDelete) != null) {
            db.dao().delete(db.dao().searchByName(nameToDelete));
        }
    }

    public void updateTextView(CharSequence nameParty) {
        PartyDatabase db = PartyDatabase.getInstance(getContext());
        Party party = db.dao().searchByName(nameParty.toString());
        Log.e(nameParty.toString(), nameParty.toString());
        shareButton.setActivated(true);
        descriptionText = party.getDescription();
        nameText = party.getName();
        shareButton.setOnClickListener(onButtonShare(nameText + " " + descriptionText));
        deleteButton.setOnClickListener(onButtonDelete());
        description.setText(descriptionText);
        name.setText(nameText);
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

    public interface DescriptionListener {
        void onInputPartyDescriptionSent(CharSequence input);
    }

}
