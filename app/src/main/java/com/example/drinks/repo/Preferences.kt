package com.example.drinks.repo

import com.example.drinks.model.Cocktail
import com.example.drinks.model.Drink
import com.example.drinks.utils.Resource

interface Preferences {
    suspend fun searchForDrink(value: String):Resource<List<Drink>>
    suspend fun getAllDrinks():Resource<List<Drink>>
    suspend fun getDrinkById(id:Int): Resource<Drink>
    suspend fun upsertDrink(drink: Drink)

    suspend fun searchForCocktail(value: String):Resource<List<Cocktail>>
    suspend fun getAllCocktails():Resource<List<Cocktail>>
    suspend fun getCocktailById(id:Int): Resource<Cocktail>
    suspend fun upsertCocktail(cocktail: Cocktail)
    suspend fun deleteCocktail(cocktail: Cocktail)

}