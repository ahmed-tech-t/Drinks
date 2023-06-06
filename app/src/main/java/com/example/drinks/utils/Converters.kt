package com.example.drinks.utils

import android.net.Uri
import androidx.room.TypeConverter
import com.example.drinks.model.DrinkCocktail
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Converters {

    @TypeConverter
    fun fromDrinkCocktailListToGson(drinkCocktail: ArrayList<DrinkCocktail>): String =
        Gson().toJson(drinkCocktail)

    @TypeConverter
    fun fromGsonToDrinkCocktailList(drinkCocktailString: String): ArrayList<DrinkCocktail> {
        val listType = object : TypeToken<ArrayList<DrinkCocktail>>() {}.type
        return Gson().fromJson(drinkCocktailString, listType)
    }

    @TypeConverter
    fun fromDrinkCocktailToGson(drinkCocktail: DrinkCocktail): String = Gson().toJson(drinkCocktail)

    @TypeConverter
    fun fromGsonToDrinkCocktail(drinkCocktailString: String): DrinkCocktail =
        Gson().fromJson(drinkCocktailString, DrinkCocktail::class.java)


}
