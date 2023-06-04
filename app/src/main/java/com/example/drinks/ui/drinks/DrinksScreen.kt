package com.example.drinks.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.drinks.R
import com.example.drinks.model.Combination
import com.example.drinks.model.Drink
import com.example.drinks.ui.drinks.DrinksViewModel
import com.example.drinks.utils.Route
import com.example.drinks.utils.UiEvent

@Composable
fun DrinksScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: DrinksViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchBar(hint = "Search") { query ->
            viewModel.search(query)
        }

        EntryGridList(onNavigate = onNavigate)
    }

}

@Composable
fun EntryGridList(
    list: List<Drink> = drinksList(),
    modifier: Modifier = Modifier,
    onNavigate: (UiEvent.Navigate) -> Unit,
) {
    LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = modifier) {
        items(list.size) { index ->
            Item(item = list[index], onNavigate = onNavigate)
        }
    }
}


@Composable
fun Item(
    item: Drink,
    modifier: Modifier = Modifier,
    onNavigate: (UiEvent.Navigate) -> Unit,
) {
    val defaultDominationColor = MaterialTheme.colorScheme.surface
    val dominationColor by remember {
        mutableStateOf(defaultDominationColor)
    }
    Box(contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp)
            .shadow(5.dp, RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .aspectRatio(1f)
            .background(
                brush = Brush.verticalGradient(
                    listOf(
                        dominationColor, defaultDominationColor
                    )
                )
            )
            .clickable {
                onNavigate(UiEvent.Navigate(Route.Details.route))
            }) {
        Column {


            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(item.image),
                    contentDescription = "images",
                    modifier = Modifier
                        .size(120.dp)
                )
            }
            Text(
                text = item.name,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}


@Composable
fun SearchBar(
    modifier: Modifier = Modifier, hint: String = "", onSearch: (String) -> Unit
) {
    var text by remember {
        mutableStateOf("")
    }
    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }

    Box(modifier = modifier) {
        BasicTextField(value = text,
            onValueChange = {
                text = it
                onSearch(it)
            },
            textStyle = TextStyle(
                color = Color.Black, fontSize = 18.sp, fontWeight = FontWeight.Medium
            ),
            singleLine = true,
            maxLines = 1,
            modifier = modifier
                .shadow(5.dp, CircleShape)
                .fillMaxWidth()
                .background(Color.White, CircleShape)
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .onFocusChanged {
                    isHintDisplayed = !it.isFocused
                })
        if (isHintDisplayed) {
            Text(
                text = hint,
                color = Color.LightGray,
                fontSize = 18.sp,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(horizontal = 20.dp, vertical = 12.dp)
            )
        }
    }
}

fun drinksList() = listOf(
    Drink(
        name = "Drink 1",
        description = "Description of Drink 1",
        image = R.drawable.img_1,
        combinations = listOf(Combination(1, 10), Combination(2, 20))
    ),
    Drink(
        name = "Drink 2",
        description = "Description of Drink 2",
        image = R.drawable.img_2,
        combinations = listOf(Combination(3, 15), Combination(4, 25))
    ),
    Drink(
        name = "Drink 3",
        description = "Description of Drink 3",
        image = R.drawable.img_3,
        combinations = listOf(Combination(3, 15), Combination(4, 25))
    ),
    Drink(
        name = "Drink 4",
        description = "Description of Drink 4",
        image = R.drawable.img_4,
        combinations = listOf(Combination(3, 15), Combination(4, 25))
    ),
    Drink(
        name = "Drink 5",
        description = "Description of Drink 5",
        image = R.drawable.img_5,
        combinations = listOf(Combination(3, 15), Combination(4, 25))
    ),
    Drink(
        name = "Drink 6",
        description = "Description of Drink 6",
        image = R.drawable.img_6,
        combinations = listOf(Combination(3, 15), Combination(4, 25))
    ),
    Drink(
        name = "Drink 7",
        description = "Description of Drink 7",
        image = R.drawable.img_7,
        combinations = listOf(Combination(3, 15), Combination(4, 25))
    ),
    Drink(
        name = "Drink 8",
        description = "Description of Drink 8",
        image = R.drawable.img_8,
        combinations = listOf(Combination(3, 15), Combination(4, 25))
    ),
    Drink(
        name = "Drink 9",
        description = "Description of Drink 9",
        image = R.drawable.img_9,
        combinations = listOf(Combination(3, 15), Combination(4, 25))
    ),
    Drink(
        name = "Drink 10",
        description = "Description of Drink 10",
        image = R.drawable.img_10,
        combinations = listOf(Combination(3, 15), Combination(4, 25))
    ),
)

