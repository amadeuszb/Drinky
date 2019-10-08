package pl.amad.drinky.activities;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import pl.amad.drinky.R;
import pl.amad.drinky.async.GsonRequest;
import pl.amad.drinky.async.RequestTast;
import pl.amad.drinky.data.dto.UserDto;

public class ListOfDrinksActivity extends AppCompatActivity {

    protected RecyclerView recyclerView;
    protected Adapter adapter;
    protected ArrayList<String> food;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_drinks);

    }
}
