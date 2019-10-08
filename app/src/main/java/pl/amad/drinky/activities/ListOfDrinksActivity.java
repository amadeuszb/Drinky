package pl.amad.drinky.activities;


import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import pl.amad.drinky.R;
import pl.amad.drinky.adapters.MyAdapter;
import pl.amad.drinky.async.GsonRequest;
import pl.amad.drinky.data.dto.DrinkDto;
import pl.amad.drinky.data.dto.DrinkListDto;


public class ListOfDrinksActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private LinkedList<DrinkDto> drinksList;
    private EditText searchText;
    private ImageView backIcon;

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
                if (!s.toString().equals(""))
                    recyclerView.setAdapter(newListToView(s.toString()));
            }
        });
        backIcon.setOnClickListener((action) -> backToLoginForm());
        recyclerView.setAdapter(newListToView(""));
    }

    private MyAdapter newListToView(String text) {
        theradGetList(text);

        return new MyAdapter(drinksList);
    }

    protected void theradGetList(String letter) {
        Context applicationContext = getApplicationContext();
        Thread x = new Thread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void run() {
                Map<String, String> headers = new ArrayMap<>();
                GsonRequest<DrinkListDto> drinkListDtoGsonRequest =
                        new GsonRequest<>("https://www.thecocktaildb.com/api/json/v1/1/search.php?s=" + letter,
                                DrinkListDto.class,
                                headers,
                                response -> {
                                    drinksList.clear();
                                    if(response.getDrinks() != null)
                                    drinksList = response.getDrinks();
                                },
                                error -> {
                                    drinksList.clear();
                                });
                RequestQueue requestQueue = Volley.newRequestQueue(applicationContext);
                requestQueue.add(drinkListDtoGsonRequest);
            }
        });
        x.start();
    }

    protected void backToLoginForm() {
        setContentView(R.layout.activity_login);
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }


}
