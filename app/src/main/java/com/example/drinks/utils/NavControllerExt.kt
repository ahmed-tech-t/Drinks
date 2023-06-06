package com.example.drinks.utils

import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.navOptions

fun NavController.navigate(event: UiEvent.Navigate) {
    this.navigate(event.route)
}


fun NavController.navigate(event: UiEvent.Navigate, builder: NavOptionsBuilder.() -> Unit) {
    this.navigate(event.route, navOptions(builder))
}

