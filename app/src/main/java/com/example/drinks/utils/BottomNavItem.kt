package com.example.drinks.utils

import com.example.drinks.R


sealed class BottomNavItem(var title: String, var icon: Int, var screen_route: String) {

    object Home : BottomNavItem("Home", R.drawable.baseline_home_24, Route.Drinks.route)
    object Add : BottomNavItem("Add", R.drawable.baseline_add_24, Route.Add.route)
    object Profile : BottomNavItem("Profile", R.drawable.ic_account, Route.Profile.route)

}