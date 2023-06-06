package com.example.drinks.utils

sealed class Route (val route:String) {

    object MainFeatures :Route("mainFeatures")

    object Drinks :Route("drinks")
    object Details :Route("details")
    object Profile :Route("profile")

    object AddFeature :Route("addFeature")
    object Add :Route("add")
    object AddDetails :Route("addDetails")

    fun withArgs(vararg args: Int?):String{
        return buildString {
            append(route)
            args.forEach {arg ->
                append("/$arg")
            }
        }
    }


}