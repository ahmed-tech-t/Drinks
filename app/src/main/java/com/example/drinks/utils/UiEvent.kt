package com.example.drinks.utils

sealed class UiEvent {
    data class Navigate(val route: String) : UiEvent()
    object NavigationUp:UiEvent()
}
