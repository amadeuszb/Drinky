package pl.amad.drinky.activities;


import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.LinkedList;
import java.util.List;

import pl.amad.drinky.R;
import pl.amad.drinky.adapters.ListElementAdapter;
import pl.amad.drinky.async.RequestAboutDrinksListTask;
import pl.amad.drinky.data.dto.DrinkDto;
import pl.amad.drinky.recievers.InternetReciever;


public class ListOfDrinksActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private LinkedList<DrinkDto> drinksList;
    private EditText searchText;
    private ImageView backIcon;
    private BroadcastReceiver mNetworkReceiver;
    public static boolean isConnectedToInternet = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_drinks);
        backIcon = findViewById(R.id.back_to_main_menu_icon);
        recyclerView = findViewById(R.id.drinks_recycler_view);
        searchText = findViewById(R.id.search_drink_text);
        recyclerView.setHasFixedSize(true);
        drinksList = new LinkedList<>();
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(isConnectedToInternet) {
                    if (!s.toString().equals(""))
                        recyclerView.setAdapter(newListToView(s.toString()));
                }
                else {
                    LinkedList<DrinkDto> noInternetLsit = new LinkedList<>();
                    DrinkDto noInternet = new DrinkDto();
                    noInternet.setStrDrink("NO INTERNET CONNECTION");
                    noInternetLsit.add(noInternet);
                   recyclerView.setAdapter(new ListElementAdapter(noInternetLsit));
                }
            }
        });

        backIcon.setOnClickListener((action) -> backToLoginForm());
        recyclerView.setAdapter(newListToView(""));
        mNetworkReceiver = new InternetReciever();
        registerNetworkBroadcastForNougat();
    }

    private ListElementAdapter newListToView(String text) {
        new RequestAboutDrinksListTask(getApplicationContext(), drinksList).execute(text);

        return new ListElementAdapter(drinksList);
    }

    protected void backToLoginForm() {
        setContentView(R.layout.activity_menu);
        finish();
    }
    private void registerNetworkBroadcastForNougat() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
    }
    @Override
    protected void onDestroy()
    {
        unregisterReceiver(mNetworkReceiver);
        super.onDestroy();
    }

}
