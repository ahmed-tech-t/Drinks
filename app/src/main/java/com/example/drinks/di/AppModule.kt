package com.example.drinks.di

import android.content.Context
import androidx.room.Room
import com.example.drinks.dp.room.CocktailDao
import com.example.drinks.dp.room.DrinkDao
import com.example.drinks.model.DrinksDatabase
import com.example.drinks.repo.DefaultPreferences
import com.example.drinks.repo.Preferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): DrinksDatabase =
        Room.databaseBuilder(
            context,
            DrinksDatabase::class.java,
            DrinksDatabase.databaseName
        )
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideDrinkDao(newsDatabase: DrinksDatabase): DrinkDao = newsDatabase.getDrinkDao()

    @Provides
    @Singleton
    fun provideCocktailDao(newsDatabase: DrinksDatabase): CocktailDao =
        newsDatabase.getCocktailDao()

    @Singleton
    @Provides
    fun providePreferences(drinkDao: DrinkDao, cocktailDao: CocktailDao): Preferences {
        return DefaultPreferences(drinkDao, cocktailDao)
    }
}