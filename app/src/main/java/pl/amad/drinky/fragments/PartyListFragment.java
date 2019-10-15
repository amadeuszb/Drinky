package pl.amad.drinky.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.LinkedList;

import pl.amad.drinky.R;
import pl.amad.drinky.dao.PartyDatabase;
import pl.amad.drinky.data.model.Party;

public class PartyListFragment extends Fragment {
    ListView listView;
    ArrayAdapter adapter;
    String[] data;
    PartyListListener listener;
    public PartyListFragment(){

    }

    public interface PartyListListener {
        void onInputPartyListSent(CharSequence input);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle savedInstanceState){
        View view = layoutInflater.inflate(R.layout.fragment_party_list,viewGroup,false);
        listView = (ListView) view.findViewById(R.id.list);
        data = new String[onSelectClick().size()];
        int d = 0;
        for(Party i: onSelectClick()){
            data[d++] = i.getName();
        }
        adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,data);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                                CharSequence input = data[position];
                                                listener.onInputPartyListSent(input);
                                            }
                                        }
        );
        listView.setAdapter(adapter);
        return view;
    }

    private LinkedList<Party> onSelectClick() {
        PartyDatabase db = PartyDatabase.getInstance(getContext());
        LinkedList<Party> allParties = new LinkedList<>();

        allParties.addAll(db.dao().getAllParties());
        return allParties;
    }
    public void refreshList(){
        data = new String[onSelectClick().size()];
        int d = 0;
        for(Party i: onSelectClick()){
            data[d++] = i.getName();
        }
        adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,data);
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
}
