package com.example.drinks.utils

import androidx.navigation.NavController

fun NavController.navigate(event: UiEvent.Navigate) {
    this.navigate(event.route)
}