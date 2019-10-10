package pl.amad.drinky.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;

import pl.amad.drinky.R;
import pl.amad.drinky.activities.MenuActivity;
import pl.amad.drinky.data.model.Party;

public class ListElementPartyAdapter extends RecyclerView.Adapter<ListElementPartyAdapter.HolderElement> {
    private LinkedList<Party> mDataset;
    private RecyclerView mRecyclerView;
    Context context;

    public static class HolderElement extends RecyclerView.ViewHolder {
        public TextView nameOfDrink;
        public long id;

        public HolderElement(TextView v) {
            super(v);
            nameOfDrink = v;
        }
    }

    public ListElementPartyAdapter(LinkedList<Party> myDataset, RecyclerView mRecyclerView, Context context) {
        mDataset = myDataset;
        this.mRecyclerView = mRecyclerView;
        this.context = context;
    }

    @NonNull
    @Override
    public HolderElement onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.drink_row, parent, false);

        v.setOnClickListener(this::onClickedElement);
        HolderElement vh = new HolderElement(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(HolderElement holder, int position) {
        holder.nameOfDrink.setText(mDataset.get(position).getName());
        holder.id = mDataset.get(position).getId();
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    private void onClickedElement(View view) {
        int itemPosition = mRecyclerView.getChildLayoutPosition(view);
        Intent i = new Intent(context, MenuActivity.class);
        i.putExtra("partyId", mDataset.get(itemPosition).getId());
        context.startActivity(i);
    }
}