package com.example.drinks.utils

sealed class Route (val route:String) {
    object Drinks :Route("drinks")
    object Details :Route("details")
    object Profile :Route("profile")
    object Add :Route("add")
    object AddDetails :Route("addDetails")


}