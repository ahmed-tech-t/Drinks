package com.example.drinks.ui.add

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
        cocktailState.value = cock
    }


    fun addNewDrink(drink: Drink) = viewModelScope.launch {
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
                drinks.value = result.data!!
            }
        }
    }

}