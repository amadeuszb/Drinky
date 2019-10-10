package pl.amad.drinky.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;

import pl.amad.drinky.R;
import pl.amad.drinky.data.dto.DrinkDto;

public class ListElementAdapter extends RecyclerView.Adapter<ListElementAdapter.HolderElement> {
    private LinkedList<DrinkDto> mDataset;

    public static class HolderElement extends RecyclerView.ViewHolder {
        public TextView nameOfDrink;
        public String id;
        public HolderElement(TextView v) {
            super(v);
            nameOfDrink = v;
        }
    }

    public ListElementAdapter(LinkedList<DrinkDto> myDataset) {
        mDataset = myDataset;
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
        holder.nameOfDrink.setText(mDataset.get(position).getStrDrink());
        holder.id = mDataset.get(position).getIdDrink();
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    private void onClickedElement(View view){

    }
}