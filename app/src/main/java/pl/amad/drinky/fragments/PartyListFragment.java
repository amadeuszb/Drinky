package pl.amad.drinky.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.LinkedList;
import java.util.Objects;

import pl.amad.drinky.R;
import pl.amad.drinky.dao.PartyDatabase;
import pl.amad.drinky.data.model.Party;

public class PartyListFragment extends Fragment {
    ListView listView;
    ArrayAdapter<String> adapter;
    String[] data;
    PartyListListener listener;

    public PartyListFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        View view = layoutInflater.inflate(R.layout.fragment_party_list, viewGroup, false);
        listView = view.findViewById(R.id.list);
        data = new String[onSelectClick().size()];
        int d = 0;
        for (Party i : onSelectClick()) {
            data[d++] = i.getName();
        }
        adapter = new ArrayAdapter<>(Objects.requireNonNull(getActivity()), android.R.layout.simple_list_item_1, data);
        listView.setOnItemClickListener((parent, view1, position, id) -> {
                    CharSequence input = data[position];
                    listener.onInputPartyListSent(input);
                }
        );
        listView.setAdapter(adapter);
        return view;
    }

    private LinkedList<Party> onSelectClick() {
        PartyDatabase db = PartyDatabase.getInstance(getContext());
        return new LinkedList<>(db.dao().getAllParties());
    }

    public void refreshList() {
        data = new String[onSelectClick().size()];
        int d = 0;
        for (Party i : onSelectClick()) {
            data[d++] = i.getName();
        }
        adapter = new ArrayAdapter<>(Objects.requireNonNull(getActivity()), android.R.layout.simple_list_item_1, data);
        listView.setAdapter(adapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof PartyListListener) {
            listener = (PartyListListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement PartyListListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface PartyListListener {
        void onInputPartyListSent(CharSequence input);
    }
}
