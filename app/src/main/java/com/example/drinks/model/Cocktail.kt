package com.example.drinks.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.inject.Inject

//@Entity(tableName = "Cocktail_Table")
data class Cocktail(
   // @@PrimaryKey(autoGenerate = true)
    val id: Int? =null,
    val image: Int ? = null,
    val name: String ? = null,
    val drinks: ArrayList<DrinkCocktail> ? = null
)