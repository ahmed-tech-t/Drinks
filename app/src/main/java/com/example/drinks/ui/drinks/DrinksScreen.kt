package com.example.drinks.ui

import android.util.Log
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.drinks.R
import com.example.drinks.model.Cocktail
import com.example.drinks.model.Drink
import com.example.drinks.ui.drinks.DrinksViewModel
import com.example.drinks.utils.Route
import com.example.drinks.utils.UiEvent
import kotlin.math.log

private const val TAG = "DrinksScreen"
@Composable
fun DrinksScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: DrinksViewModel
) {
    val cocktailListState = remember {
        viewModel.cocktailListState
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchBar(hint = "Search", modifier = Modifier.padding(vertical = 10.dp)) { query ->
            viewModel.search(query)
        }
        cocktailListState.value?.let { EntryGridList(list = it, onNavigate = onNavigate) }
    }

}

@Composable
fun EntryGridList(
    list: List<Cocktail>,
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
    item: Cocktail,
    modifier: Modifier = Modifier,
    viewModel: DrinksViewModel = hiltViewModel(),
    onNavigate: (UiEvent.Navigate) -> Unit,
) {
    val defaultDominationColor = MaterialTheme.colorScheme.surface
    var dominationColor by remember {
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
                onNavigate(UiEvent.Navigate(Route.Details.withArgs(item.id)))
            }) {
        Column {
            val painter = rememberAsyncImagePainter(
                model = item.image?.toUri(),
                onSuccess = { result ->
                    Log.d(TAG, "Item: ${item.image}")
                    viewModel.calcDominantColor(result.result.drawable) { color ->
                        dominationColor = color
                    }

                }, onError = {
                    Log.d(TAG, "Item: ${it.result.throwable.message}")
                })
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Image(
                    painter = painter,
                    contentDescription = "images",
                    modifier = Modifier.size(120.dp)
                )
            }
            item.name?.let {
                Text(
                    text = it,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
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
    Drink(
        name = "Drink 7",
        description = "Description of Drink 7",
        image = R.drawable.img_7,
    ),
    Drink(
        name = "Drink 8",
        description = "Description of Drink 8",
        image = R.drawable.img_8,
    ),
    Drink(
        name = "Drink 9",
        description = "Description of Drink 9",
        image = R.drawable.img_9,
    ),
    Drink(
        name = "Drink 10",
        description = "Description of Drink 10",
        image = R.drawable.img_10,
    ),
)

