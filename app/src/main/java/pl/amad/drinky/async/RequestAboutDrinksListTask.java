package pl.amad.drinky.async;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.util.ArrayMap;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.LinkedList;
import java.util.Map;

import pl.amad.drinky.data.dto.DrinkDto;
import pl.amad.drinky.data.dto.DrinkListDto;

public class RequestAboutDrinksListTask extends AsyncTask<String, String, LinkedList<DrinkDto>> {

    private Context mContext;
    LinkedList<DrinkDto> drinksList;

    public RequestAboutDrinksListTask(Context context, LinkedList<DrinkDto> drinksList) {
        mContext = context;
        this.drinksList = drinksList;
    }


    @Override
    protected LinkedList<DrinkDto> doInBackground(String... name) {
        Map<String, String> headers = new ArrayMap<>();
        GsonRequest<DrinkListDto> drinkListDtoGsonRequest =
                new GsonRequest<>("https://www.thecocktaildb.com/api/json/v1/1/search.php?s=" + name[0],
                        DrinkListDto.class,
                        headers,
                        response -> {
                            drinksList.clear();
                            if (response.getDrinks() != null)
                                drinksList.addAll(response.getDrinks());
                        },
                        error -> {
                            drinksList.clear();
                        });
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        requestQueue.add(drinkListDtoGsonRequest);
        return drinksList;
    }


    @Override
    protected void onPostExecute(LinkedList<DrinkDto> result) {
        super.onPostExecute(result);
        //Do anything with response..
    }
}