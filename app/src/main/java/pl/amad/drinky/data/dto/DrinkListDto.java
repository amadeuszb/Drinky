package pl.amad.drinky.data.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.LinkedList;
import java.util.List;

public class DrinkListDto {
    @SerializedName("drinks")
    @Expose
    private LinkedList<DrinkDto> drinks = null;

    public LinkedList<DrinkDto> getDrinks() {
        return drinks;
    }

    public void setDrinks(LinkedList<DrinkDto> drinks) {
        this.drinks = drinks;
    }

}
