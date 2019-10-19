package pl.amad.drinky.async;

import android.content.Context;
import android.os.AsyncTask;

import java.util.LinkedList;

import pl.amad.drinky.dao.PartyDatabase;
import pl.amad.drinky.data.model.Party;
//TODO
public class RequestAboutParties extends AsyncTask<String, String, LinkedList<Party>> {

    private Context mContext;
    private LinkedList<Party> partyList;

    public RequestAboutParties(Context context, LinkedList<Party> partyList) {
        mContext = context;
        this.partyList = partyList;
    }

    @Override
    protected LinkedList<Party> doInBackground(String... strings) {
        PartyDatabase db = PartyDatabase.getInstance(mContext);
        LinkedList<Party> allParties = new LinkedList<>();
        allParties.addAll(db.dao().getAllParties());
        return allParties;
    }
}
