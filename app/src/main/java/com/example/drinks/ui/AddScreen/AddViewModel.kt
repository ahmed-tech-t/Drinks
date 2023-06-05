package com.example.drinks.ui.AddScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.drinks.model.Cocktail
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(

) : ViewModel() {
    var cocktail by mutableStateOf<Cocktail?>(null)
        private set

    fun addCocktail(cock: Cocktail?) {
        cocktail = cock
    }

}