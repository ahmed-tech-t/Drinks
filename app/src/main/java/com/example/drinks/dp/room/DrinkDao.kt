package com.example.drinks.dp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.drinks.model.Cocktail
import com.example.drinks.model.Drink
import com.example.drinks.utils.Cocktail_TABLE
import com.example.drinks.utils.Drinks_TABLE

@Dao
interface DrinkDao {
    @Query("select * from $Drinks_TABLE")
    suspend fun getAllDrinks():List<Drink>


    @Query("SELECT * FROM $Drinks_TABLE WHERE 'id' = :drinkId")
    suspend fun getDrinkById(drinkId: Int):Drink


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertDrink(drink: Drink): Long

    @Query("SELECT * FROM $Drinks_TABLE WHERE 'name' LIKE '%' || :value || '%'")
    suspend fun search(value: String): List<Drink>

    @Delete
    suspend fun deleteDrink(drink: Drink)
}