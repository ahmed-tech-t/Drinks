package com.example.drinks.ui.add

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
import com.example.drinks.model.Cocktail
import com.example.drinks.model.Drink
import com.example.drinks.model.DrinkCocktail
import com.example.drinks.ui.components.RightButton
import com.example.drinks.ui.components.ValidateText
import com.example.drinks.utils.Route
import com.example.drinks.utils.UiEvent


@Composable
fun AddScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: AddViewModel
) {

    var cocktailState by remember {
        mutableStateOf(Cocktail(drinks = ArrayList()))
    }

    val drinksList by remember { viewModel.drinks }

    var validCocktail by remember {
        mutableStateOf(false)
    }
    var drinksCountInCocktail by remember {
        mutableStateOf(0)
    }
    var buttonClicked by remember {
        mutableStateOf(false)
    }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()

    ) {
        Spacer(modifier = Modifier.height((30.dp)))
        Text(text = "Make your drink", fontSize = 18.sp, fontWeight = FontWeight.Medium)
        Spacer(modifier = Modifier.height(20.dp))
        DrinksGridList(list = drinksList) { cocktail ->
            cocktailState = cocktail
        }

        ValidateText(
            validCocktail = validCocktail,
            buttonClicked = buttonClicked,
            text = "please chose more than one drink first"
        )

        RightButton(text = "Next") {
            buttonClicked = true
            cocktailState.drinks.let {
                for (i in cocktailState.drinks) {
                    if (i.quantity > 0) {

                        drinksCountInCocktail++
                    }
                }
                if (drinksCountInCocktail > 1) {
                    validCocktail = true
                    viewModel.addCocktail(cocktailState)
                    onNavigate(UiEvent.Navigate(Route.AddDetails.route))
                } else {
                    validCocktail = false
                }
            }
        }
    }

}

@Composable
fun DrinksGridList(
    list: List<Drink>,
    modifier: Modifier = Modifier,
    cocktail: (Cocktail) -> Unit
) {

    val cocktailState by remember {
        mutableStateOf(Cocktail(drinks = ArrayList()))
    }
    LazyVerticalGrid(columns = GridCells.Fixed(3), modifier = modifier) {
        items(list.size) { index ->
            DrinkItem(item = list[index]) { newDrinkCocktail ->
                val pos: Int = cocktailState.drinks.indexOf(newDrinkCocktail)
                if (pos != -1) {
                    pos.let {
                        cocktailState.drinks[it] = newDrinkCocktail
                        cocktail(cocktailState)
                    }
                } else {
                    cocktailState.drinks.add(newDrinkCocktail)
                    cocktail(cocktailState)
                }
            }
        }
    }
}

@Composable
fun DrinkItem(
    item: Drink,
    currentDrinkCocktail: (DrinkCocktail) -> Unit
) {
    var counter by remember {
        mutableStateOf(0)
    }
    val drinkCocktailState by remember {
        mutableStateOf(DrinkCocktail(item.id, counter))
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
                if (counter > 0) {
                    counter--
                    drinkCocktailState.quantity = counter
                }
                currentDrinkCocktail(drinkCocktailState)
            }
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = counter.toString(), fontSize = 18.sp, fontWeight = FontWeight.Medium)
            Spacer(modifier = Modifier.width(5.dp))
            MinMaxButton(text = "+") {
                counter++
                drinkCocktailState.quantity = counter
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
