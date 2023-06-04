package com.example.drinks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.drinks.ui.DrinksScreen
import com.example.drinks.ui.theme.DrinksTheme
import com.example.drinks.utils.Route
import com.example.drinks.utils.navigate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DrinksTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Route.Drinks.route) {
                    composable(route = Route.Drinks.route) {
                        DrinksScreen(onNavigate = navController::navigate)
                    }
                    composable(route = Route.Details.route) {
                    }
                }
            }
        }
    }
}
