package com.example.drinks.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.drinks.R
import com.example.drinks.model.Cocktail
import com.example.drinks.model.Drink
import com.example.drinks.model.DrinkCocktail
import com.example.drinks.ui.AddScreen.AddViewModel
import com.example.drinks.ui.components.MainButton
import com.example.drinks.utils.Route
import com.example.drinks.utils.UiEvent


@Composable
fun AddScreen(
    onNavigate: (UiEvent.Navigate) -> Unit, viewModel: AddViewModel = hiltViewModel()
) {
    var cocktailState by remember {
        mutableStateOf<Cocktail?>(null)
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.TopCenter)

        ) {
            Spacer(modifier = Modifier.height((30.dp)))
            Text(text = "Make your drink", fontSize = 18.sp, fontWeight = FontWeight.Medium)
            Spacer(modifier = Modifier.height(20.dp))
            DrinksGridList() { cocktail ->
                cocktailState = cocktail
            }
        }
        MainButton(
            text = "Next", modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            //TODO CHECK IF THERE IS ANY ADDED DRINK TO THIS COCKTAIL BEFORE GOTO NEXT PAGE
            viewModel.addCocktail(cocktailState)
            onNavigate(UiEvent.Navigate(Route.AddDetails.route))
        }
    }
}

@Composable
fun DrinksGridList(
    list: List<Drink> = drinksList_1(), modifier: Modifier = Modifier, cocktail: (Cocktail?) -> Unit
) {

    val cocktailState by remember {
        mutableStateOf<Cocktail?>(null)
    }
    LazyVerticalGrid(columns = GridCells.Fixed(3), modifier = modifier) {
        items(list.size) { index ->
            DrinkItem(item = list[index]) { newDrinkCocktail ->
                val pos: Int? = cocktailState?.drinks?.indexOf(newDrinkCocktail)
                if (pos != -1) {
                    pos?.let {
                        cocktailState?.drinks?.set(it, newDrinkCocktail)
                        cocktail(cocktailState)
                    }
                } else {
                    cocktailState?.drinks?.add(newDrinkCocktail)
                    cocktail(cocktailState)
                }
            }
        }
    }
}

@Composable
fun DrinkItem(
    item: Drink, currentDrinkCocktail: (DrinkCocktail) -> Unit
) {
    var counter by remember {
        mutableStateOf(0)
    }
    var drinkCocktailState by remember {
        mutableStateOf(DrinkCocktail(item, counter))
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = item.name, fontSize = 18.sp, fontWeight = FontWeight.Medium)
        Spacer(modifier = Modifier.height(10.dp))
        Image(
            painter = painterResource(id = item.image),
            contentDescription = item.description,
            modifier = Modifier
                .size(120.dp)
                .clip(shape = RoundedCornerShape(20.dp))

        )
        Spacer(modifier = Modifier.height((10.dp)))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            MinMaxButton(text = "-") {
                if (counter > 0) counter--
                currentDrinkCocktail(drinkCocktailState)
            }
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = counter.toString(), fontSize = 18.sp, fontWeight = FontWeight.Medium)
            Spacer(modifier = Modifier.width(5.dp))
            MinMaxButton(text = "+") {
                counter++
                currentDrinkCocktail(drinkCocktailState)
            }
        }
        Spacer(modifier = Modifier.height((20.dp)))

    }


}

@Composable
fun MinMaxButton(text: String, onclick: () -> Unit) {
    Box(contentAlignment = Alignment.CenterStart,
        modifier = Modifier
            .size(30.dp)
            .aspectRatio(1f)
            .clip(CircleShape)
            .background(Color.LightGray)
            .clickable {
                onclick()
            }) {
        Text(
            text = text,
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp,
            modifier = Modifier.align(
                Alignment.Center
            )
        )
    }

}


fun drinksList_1() = listOf(
    Drink(
        name = "Drink 1",
        description = "Description of Drink 1",
        image = R.drawable.img_1,
    ),
    Drink(
        name = "Drink 2",
        description = "Description of Drink 2",
        image = R.drawable.img_2,
    ),
    Drink(
        name = "Drink 3",
        description = "Description of Drink 3",
        image = R.drawable.img_3,
    ),
    Drink(
        name = "Drink 4",
        description = "Description of Drink 4",
        image = R.drawable.img_4,
    ),
    Drink(
        name = "Drink 5",
        description = "Description of Drink 5",
        image = R.drawable.img_5,
    ),
    Drink(
        name = "Drink 6",
        description = "Description of Drink 6",
        image = R.drawable.img_6,
    ),


    )

