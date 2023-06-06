package com.example.drinks.ui.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.drinks.model.Drink
import com.example.drinks.model.DrinkCocktail
import com.example.drinks.ui.drinks.DrinksViewModel

@Composable
fun DetailsScreen(
    viewModel: DrinksViewModel,
    cocktailId: Int
) {
    val cocktailState = remember {
        viewModel.cocktailState
    }
    LaunchedEffect(true) {
        viewModel.getCocktailById(cocktailId)
    }

    val cocktailDrinksState = remember {
        viewModel.drinksListState
    }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize()
    ) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {
            Text(
                text = "Состав",
                fontSize = 18.sp,
                modifier = Modifier.padding(10.dp),
                color = Color.DarkGray
            )
        }
        cocktailState.value?.let {
            CocktailDrinks(
                ListDrinkCocktail = it.drinks,
                listDrink = cocktailDrinksState
            )
        }

        Button(
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.Black
            ),
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .padding(10.dp),
            onClick = {

            }) {
            Text(text = "Сделат", color = Color.White)
        }
    }
}

@Composable
fun CocktailDrinks(
    ListDrinkCocktail: List<DrinkCocktail>,
    listDrink: SnapshotStateList<Drink>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(ListDrinkCocktail.size) { index ->
            val drinkCocktail = ListDrinkCocktail[index]
            val drink = listDrink[index]
            CocktailDrinksItem(name = drink.name, quantity = drinkCocktail.quantity)
        }
    }
}

@Composable
fun CocktailDrinksItem(
    name: String,
    quantity: Int,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ) {
        Text(text = name, fontSize = 15.sp, color = Color.Black)
        Text(text = "$quantity ml", fontSize = 15.sp, color = Color.Black)
    }
}
