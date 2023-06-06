package com.example.drinks.ui.drinks

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import com.example.drinks.model.Cocktail
import com.example.drinks.model.Drink
import com.example.drinks.model.DrinkCocktail
import com.example.drinks.repo.Preferences
import com.example.drinks.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrinksViewModel @Inject constructor(
    private val repo: Preferences
) : ViewModel() {
    private val TAG = "DrinksViewModel"
    val searchState = mutableStateOf<List<Cocktail>>(listOf())
    var drinksListState = mutableStateListOf<Drink>()
        private set
    var cocktailState = mutableStateOf<Cocktail?>(null)
        private set
    var cocktailListState = mutableStateOf<List<Cocktail>?>(null)
        private set

    init {

        getAllCocktails()
    }

    fun search(value: String) = viewModelScope.launch {
        searchState.value = repo.searchForCocktail(value).data!!
    }

    fun getCocktailById(id: Int) = viewModelScope.launch {
        when (val result = repo.getCocktailById(id)) {
            is Resource.Error -> {
                Log.d(TAG, "getCocktailById: ${result.message}")
            }

            is Resource.Loading -> TODO()
            is Resource.Success -> {
                cocktailState.value = result.data
                cocktailState.value?.let { fetchDrinksByIds(it.drinks) }
            }
        }
    }

    private fun getAllCocktails() = viewModelScope.launch {
        when (val result = repo.getAllCocktails()) {
            is Resource.Error -> {
                Log.e(TAG, "getAllCocktails: ${result.message}")
            }

            is Resource.Loading -> TODO()
            is Resource.Success -> {
                Log.d(TAG, "getAllCocktails: ${result.data?.size}")
                cocktailListState.value = result.data
            }
        }
    }

    private fun deleteCocktail(cocktail: Cocktail) = viewModelScope.launch {
        repo.deleteCocktail(cocktail)
    }

    private fun getDrinkById(id: Int) = viewModelScope.launch {
        when (val result = repo.getDrinkById(id)) {
            is Resource.Error -> {
                Log.d(TAG, "getDrinkById: ${result.message}")
            }

            is Resource.Loading -> TODO()
            is Resource.Success -> {
                result.data?.let { drinksListState.add(it) }
            }
        }
    }

    private fun fetchDrinksByIds(drinkCocktail: List<DrinkCocktail>) {
        viewModelScope.launch {
            drinksListState.clear()
            for (i in drinkCocktail) {
                i.drinkId?.let { getDrinkById(it) }
            }
        }
    }

    fun calcDominantColor(drawable: Drawable, onFinish: (Color) -> Unit) {
        val bmp = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)

        Palette.from(bmp).generate { palette ->
            palette?.dominantSwatch?.rgb?.let { colorValue ->
                onFinish(Color(colorValue))
            }
        }
    }
}