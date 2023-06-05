package com.example.drinks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.drinks.ui.add.AddDetailsScreen
import com.example.drinks.ui.add.AddScreen
import com.example.drinks.ui.BottomNavigationBar
import com.example.drinks.ui.DrinksScreen
import com.example.drinks.ui.theme.DrinksTheme
import com.example.drinks.utils.BottomNavItem
import com.example.drinks.utils.Route
import com.example.drinks.utils.navigate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DrinksTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(
                            items = listOf(
                                BottomNavItem.Home,
                                BottomNavItem.Add,
                                BottomNavItem.Profile
                            ),
                            navController = navController,
                            onItemClick = navController::navigate
                        )
                    }, content = { padding ->
                        Box(modifier = Modifier.padding(padding)) {
                            Navigation(navController = navController)
                        }
                    }
                )
            }
        }
    }

    @Composable
    fun Navigation(
        navController: NavHostController
    ) {
        NavHost(
            navController = navController,
            startDestination = Route.Drinks.route
        ) {
            composable(route = Route.Drinks.route) {
                DrinksScreen(onNavigate = navController::navigate)
            }
            composable(route = Route.Details.route) {
            }
            composable(route = Route.Profile.route) {
            }
            composable(route = Route.Add.route) {
                AddScreen(onNavigate = navController::navigate)
            }
            composable(route = Route.AddDetails.route) {
                AddDetailsScreen(onNavigate = navController::navigate)
            }
        }
    }
}
