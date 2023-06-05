package com.example.drinks.model

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.drinks.dp.room.CocktailDao
import com.example.drinks.dp.room.DrinkDao
import com.example.drinks.utils.Converters
import com.example.drinks.utils.DATABASE_NAME


@Database(
    entities = [Drink::class, Cocktail::class],
    version = 3
)
@TypeConverters(Converters::class)
abstract class DrinksDatabase : RoomDatabase() {

    abstract fun getDrinkDao(): DrinkDao
    abstract fun getCocktailDao(): CocktailDao

    companion object {
        const val databaseName: String = DATABASE_NAME
    }
}