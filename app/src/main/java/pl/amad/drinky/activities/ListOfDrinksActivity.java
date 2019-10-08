package pl.amad.drinky.activities;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import pl.amad.drinky.R;
import pl.amad.drinky.adapters.MyAdapter;
import pl.amad.drinky.data.dto.DrinkDto;
import pl.amad.drinky.services.DrinksService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ListOfDrinksActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<String> drinksList;
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
                recyclerView.setAdapter(newListToView(s.toString()));
            }
        });
        backIcon.setOnClickListener((action) -> backToLoginForm());
        recyclerView.setAdapter(newListToView(""));
    }

    private MyAdapter newListToView(String text) {
        drinksList.add(text);
        drinksList.add(callForDrinksList(text));
        String[] myDataset = new String[1];
        if (text.equals("")) drinksList = new LinkedList<>();
        return new MyAdapter(drinksList.toArray(myDataset));
    }

    protected String callForDrinksList(String drinkName) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DrinksService service = retrofit.create(DrinksService.class);
        Call<List<DrinkDto>> callForDrinkList = service.listRepos(drinkName);
        callForDrinkList
                .enqueue(new Callback<List<DrinkDto>>() {
                    @Override
                    public void onResponse(Call<List<DrinkDto>> call, Response<List<DrinkDto>> response) {
                        drinksList.add(response.body().get(0).getStrDrink());
                    }

                    @Override
                    public void onFailure(Call<List<DrinkDto>> call, Throwable throwable) {
                        drinksList.add("Dupa");
                    }
                });
        return "";
    }

    protected void backToLoginForm() {
        setContentView(R.layout.activity_main);

        // startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}
