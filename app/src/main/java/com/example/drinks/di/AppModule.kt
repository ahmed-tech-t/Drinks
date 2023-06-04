package com.example.drinks.di

import android.content.Context
import androidx.room.Room
import com.example.drinks.dp.room.DaoApi
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
    fun provideDao(newsDatabase: DrinksDatabase): DaoApi = newsDatabase.getDaoApi()

    @Singleton
    @Provides
    fun providePreferences(daoApi: DaoApi): Preferences {
        return DefaultPreferences(daoApi)
    }
}