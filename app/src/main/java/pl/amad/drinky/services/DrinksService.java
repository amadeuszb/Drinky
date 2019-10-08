package pl.amad.drinky.services;

import java.util.List;

import pl.amad.drinky.data.dto.DrinkDto;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DrinksService {

    @GET("search.php")
    Call<List<DrinkDto>> listRepos(@Query("drinkName") String drinkName);
}