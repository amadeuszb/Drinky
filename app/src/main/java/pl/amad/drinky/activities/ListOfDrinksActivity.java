package pl.amad.drinky.activities;


import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.LinkedList;

import pl.amad.drinky.R;
import pl.amad.drinky.adapters.ListElementAdapter;
import pl.amad.drinky.async.RequestAboutDrinksListTask;
import pl.amad.drinky.data.dto.DrinkDto;
import pl.amad.drinky.recievers.InternetConnectionReceiver;


public class ListOfDrinksActivity extends AppCompatActivity {

    public static boolean isConnectedToInternet = true;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private LinkedList<DrinkDto> drinksList;
    private EditText searchText;
    private ImageView backIcon;
    private BroadcastReceiver mNetworkReceiver;
    private Button buttonSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_drinks);
        buttonSearch = findViewById(R.id.search_party_button);
        backIcon = findViewById(R.id.back_to_main_menu_icon);
        recyclerView = findViewById(R.id.drinks_recycler_view);
        searchText = findViewById(R.id.search_drink_text);
        recyclerView.setHasFixedSize(true);
        drinksList = new LinkedList<>();
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        buttonSearch.setOnClickListener(buttonSearchListener());
        backIcon.setOnClickListener((action) -> backToLoginForm());
        recyclerView.setAdapter(newListToView(""));
        mNetworkReceiver = new InternetConnectionReceiver();
        registerNetworkBroadcastForNougat();
    }

    private View.OnClickListener buttonSearchListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConnectedToInternet) {
                    if (!searchText.getText().toString().equals(""))
                        recyclerView.setAdapter(newListToView(searchText.getText().toString()));
                } else {
                    LinkedList<DrinkDto> noInternetLsit = new LinkedList<>();
                    DrinkDto noInternet = new DrinkDto();
                    noInternet.setStrDrink("NO INTERNET CONNECTION");
                    noInternetLsit.add(noInternet);
                    recyclerView.setAdapter(new ListElementAdapter(noInternetLsit));
                }
            }
        };
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
    protected void onDestroy() {
        unregisterReceiver(mNetworkReceiver);
        super.onDestroy();
    }


}
