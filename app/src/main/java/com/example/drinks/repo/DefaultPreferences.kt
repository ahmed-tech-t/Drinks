package com.example.drinks.repo

import android.util.Log
import com.example.drinks.dp.room.CocktailDao
import com.example.drinks.dp.room.DrinkDao
import com.example.drinks.model.Cocktail
import com.example.drinks.model.Drink
import com.example.drinks.utils.Resource
import javax.inject.Inject


class DefaultPreferences @Inject constructor(
    private val drinkDao: DrinkDao,
    private val cocktailDao: CocktailDao
) : Preferences {
    private  val TAG = "DefaultPreferences"
    override suspend fun searchForDrink(value: String): Resource<List<Drink>> {
        val result = try {
            drinkDao.search(value)
        } catch (ex: Exception) {
            return Resource.Error(message = "Error ${ex.message} ")
        }
        return Resource.Success(result)
    }

    override suspend fun getAllDrinks(): Resource<List<Drink>> {
        val result = try {
            drinkDao.getAllDrinks()
        } catch (ex: Exception) {
            return Resource.Error(message = "Error ${ex.message} ")
        }

        return Resource.Success(result)
    }

    override suspend fun getDrinkById(id: Int): Resource<Drink> {
        val result = try {
            drinkDao.getDrinkById(id)
        } catch (ex: Exception) {
            return Resource.Error(message = "Error ${ex.message} ")
        }
        return Resource.Success(result)
    }

    override suspend fun upsertDrink(drink: Drink) {
        drinkDao.upsertDrink(drink)
    }

    override suspend fun searchForCocktail(value: String): Resource<List<Cocktail>> {
        val result = try {
            cocktailDao.search(value)
        } catch (ex: Exception) {
            return Resource.Error(message = "Error ${ex.message} ")
        }
        return Resource.Success(result)
    }

    override suspend fun getAllCocktails(): Resource<List<Cocktail>> {
        val result = try {
            cocktailDao.getAllCocktails()
        } catch (ex: Exception) {
            return Resource.Error(message = "Error ${ex.message} ")
        }
        return Resource.Success(result)
    }

    override suspend fun getCocktailById(id: Int): Resource<Cocktail> {
        val result = try {
            cocktailDao.getCocktailById(id)
        } catch (ex: Exception) {
            return Resource.Error(message = "Error ${ex.message} ")
        }
        return Resource.Success(result)
    }

    override suspend fun upsertCocktail(cocktail: Cocktail) {
        cocktailDao.upsertCocktail(cocktail)
    }
}