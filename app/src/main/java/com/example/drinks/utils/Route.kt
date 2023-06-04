package com.example.drinks.utils

sealed class Route (val route:String) {
    object Drinks :Route("drinks")
    object Details :Route("details")

}