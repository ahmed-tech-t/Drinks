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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.drinks.ui.BottomNavigationBar
import com.example.drinks.ui.DrinksScreen
import com.example.drinks.ui.add.AddDetailsScreen
import com.example.drinks.ui.add.AddScreen
import com.example.drinks.ui.add.AddViewModel
import com.example.drinks.ui.details.DetailsScreen
import com.example.drinks.ui.drinks.DrinksViewModel
import com.example.drinks.ui.theme.DrinksTheme
import com.example.drinks.utils.BottomNavItem
import com.example.drinks.utils.Route
import com.example.drinks.utils.navigate
import com.example.drinks.utils.sharedViewModel
import dagger.hilt.android.AndroidEntryPoint

const val COCKTAIL_ID = "cocktailId"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DrinksTheme {
                val navController = rememberNavController()
                Scaffold(bottomBar = {
                    BottomNavigationBar(
                        items = listOf(
                            BottomNavItem.Home, BottomNavItem.Add, BottomNavItem.Profile
                        ), navController = navController, onItemClick = navController::navigate
                    )
                }, content = { padding ->
                    Box(modifier = Modifier.padding(padding)) {
                        Navigation(navController = navController)
                    }
                })
            }
        }
    }

    @Composable
    fun Navigation(
        navController: NavHostController
    ) {
        NavHost(
            navController = navController, startDestination = Route.MainFeatures.route
        ) {
            navigation(startDestination = Route.Add.route, route = Route.AddFeature.route) {
                composable(route = Route.Add.route) {
                    val viewModel = it.sharedViewModel<AddViewModel>(navController = navController)
                    AddScreen(onNavigate = navController::navigate, viewModel = viewModel)
                }
                composable(route = Route.AddDetails.route) {
                    val viewModel = it.sharedViewModel<AddViewModel>(navController = navController)
                    AddDetailsScreen(onNavigate = { event ->
                        navController.navigate(event) {
                            popUpTo(Route.AddFeature.route) {
                                inclusive = true
                            }
                        }

                    }, viewModel = viewModel)
                }
            }
            navigation(startDestination = Route.Drinks.route, route = Route.MainFeatures.route) {
                composable(route = Route.Drinks.route) {
                    val viewModel =
                        it.sharedViewModel<DrinksViewModel>(navController = navController)
                    DrinksScreen(viewModel = viewModel, onNavigate = navController::navigate)
                }
                composable(
                    route = Route.Details.route + "/{$COCKTAIL_ID}", arguments = listOf(
                        navArgument(COCKTAIL_ID) {
                            type = NavType.IntType
                            defaultValue = 0
                            nullable = false
                        }
                    )
                ) { entry ->
                    val viewModel =
                        entry.sharedViewModel<DrinksViewModel>(navController = navController)
                    entry.arguments?.let { DetailsScreen(viewModel, it.getInt(COCKTAIL_ID)) }
                }
            }
            composable(route = Route.Profile.route) {}
        }
    }
}
