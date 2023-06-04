package com.example.drinks.repo

import com.example.drinks.model.Drink
import com.example.drinks.utils.Resource

interface Preferences {
    suspend fun search(value: String):Resource<List<Drink>>
    suspend fun getAllDrinks():Resource<List<Drink>>
    suspend fun getDrinkById(id:Int): Resource<Drink>
    suspend fun addDrink(drink: Drink)
    suspend fun editDrink(drink: Drink)

}