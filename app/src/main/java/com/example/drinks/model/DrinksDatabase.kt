package com.example.drinks.model

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.drinks.dp.room.DaoApi
import com.example.drinks.utils.Converters
import com.example.drinks.utils.DATABASE_NAME


@Database(
    entities = [Drink::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class DrinksDatabase : RoomDatabase() {

    abstract fun getDaoApi(): DaoApi

    companion object {
        const val databaseName: String = DATABASE_NAME
    }
}