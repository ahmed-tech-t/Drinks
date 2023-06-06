package com.example.drinks.ui.add

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.drinks.R
import com.example.drinks.model.Cocktail
import com.example.drinks.model.Drink
import com.example.drinks.repo.Preferences
import com.example.drinks.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    private val repo: Preferences
) : ViewModel() {
    private val TAG = "AddViewModel"
    var cocktailState = mutableStateOf(Cocktail(drinks = ArrayList()))
        private set
    var drinks = mutableStateOf<List<Drink>>(listOf())
        private set

    init {
        getAllDrinks()
    }

    fun addCocktail(cock: Cocktail) {
        Log.d(TAG, "addCocktail: ${cock.drinks}")
        cocktailState.value = cock
    }


    private fun addNewDrink(drink: Drink) = viewModelScope.launch {
        repo.upsertDrink(drink)
    }

    fun addNewCocktail(cocktail: Cocktail) = viewModelScope.launch {
        repo.upsertCocktail(cocktail)
    }

    private fun getAllDrinks() = viewModelScope.launch {
        when (val result = repo.getAllDrinks()) {
            is Resource.Error -> Log.d(TAG, "getAllDrinks: ${result.message}")
            is Resource.Loading -> {}
            is Resource.Success -> {
                if (result.data?.isEmpty() == true) {
                    for (i in drinksList()) addNewDrink(i)
                }
                drinks.value = result.data!!
            }
        }
    }

    private fun drinksList() = listOf(
        Drink(
            name = "Drink 1",
            description = "Description of Drink 1",
            image = R.drawable.img_1,
        ),
        Drink(
            name = "Drink 2",
            description = "Description of Drink 2",
            image = R.drawable.img_2,
        ),
        Drink(
            name = "Drink 3",
            description = "Description of Drink 3",
            image = R.drawable.img_3,
        ),
        Drink(
            name = "Drink 4",
            description = "Description of Drink 4",
            image = R.drawable.img_4,
        ),
        Drink(
            name = "Drink 5",
            description = "Description of Drink 5",
            image = R.drawable.img_5,
        ),
        Drink(
            name = "Drink 6",
            description = "Description of Drink 6",
            image = R.drawable.img_6,
        )
    )

}