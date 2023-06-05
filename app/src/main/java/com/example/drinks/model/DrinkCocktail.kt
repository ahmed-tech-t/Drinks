package com.example.drinks.model

import javax.inject.Inject

data class DrinkCocktail @Inject constructor(
    val drink:Drink ,
    val quantity:Int
)
