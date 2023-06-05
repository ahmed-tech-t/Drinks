package com.example.drinks.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.drinks.utils.BottomNavItem
import com.example.drinks.utils.UiEvent


@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (UiEvent.Navigate) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()

    NavigationBar(
        modifier = modifier,
        containerColor = Color.White,
        tonalElevation = 5.dp
    ) {
        items.forEach { item ->
            val selected = item.screen_route == backStackEntry.value?.destination?.route
            NavigationBarItem(

                selected = selected,
                onClick = { onItemClick(UiEvent.Navigate(item.screen_route)) },
                icon = {
                    Column(horizontalAlignment = CenterHorizontally) {
                        Icon(
                            painter = painterResource(id = item.icon),
                            tint = if (selected) Color.Black else Color.DarkGray,
                            contentDescription = item.title
                        )
                        if (selected) {
                            Text(
                                text = item.title,
                                textAlign = TextAlign.Center,
                                fontSize = 10.sp
                            )
                        }
                    }

                }
            )
        }
    }
}
