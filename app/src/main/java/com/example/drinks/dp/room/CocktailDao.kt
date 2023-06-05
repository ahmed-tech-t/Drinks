package com.example.drinks.dp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.drinks.model.Cocktail
import com.example.drinks.model.Drink
import com.example.drinks.utils.Cocktail_TABLE

@Dao
interface CocktailDao {
    @Query("select * from $Cocktail_TABLE")
    suspend fun getAllCocktails(): List<Cocktail>

    @Query("SELECT * FROM $Cocktail_TABLE WHERE 'id' = :cocktailId")
    suspend fun getCocktailById(cocktailId: Int): Cocktail

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertCocktail(cocktail: Cocktail): Long

    @Query("SELECT * FROM $Cocktail_TABLE WHERE 'name' LIKE '%' || :value || '%'")
    suspend fun search(value: String): List<Cocktail>

    @Delete
    suspend fun deleteCocktail(cocktail: Cocktail)
}