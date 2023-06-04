package com.example.drinks.repo

import com.example.drinks.dp.room.DaoApi
import com.example.drinks.model.Drink
import com.example.drinks.utils.Resource

class DefaultPreferences(
    private val daoApi: DaoApi
) : Preferences {
    override suspend fun search(value: String): Resource<List<Drink>> {
        val result = try {
            daoApi.search(value)
        } catch (ex: Exception) {
            return Resource.Error(message = "Error ${ex.message} ")
        }
        return Resource.Success(result)
    }

    override suspend fun getAllDrinks(): Resource<List<Drink>> {
        val result = try {
            daoApi.getAllDrinks()
        } catch (ex: Exception) {
            return Resource.Error(message = "Error ${ex.message} ")
        }
        return Resource.Success(result)
    }


    override suspend fun getDrinkById(id: Int): Resource<Drink> {
        val result = try {
            daoApi.getDrinkById(id)
        } catch (ex: Exception) {
            return Resource.Error(message = "Error ${ex.message} ")
        }
        return Resource.Success(result)
    }

    override suspend fun addDrink(drink: Drink) {
        daoApi.upsertNewDrink(drink)
    }

    override suspend fun editDrink(drink: Drink) {
        daoApi.upsertNewDrink(drink)
    }


}