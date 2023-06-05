package com.example.drinks.ui.AddScreen

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.drinks.R
import com.example.drinks.ui.components.MainButton
import com.example.drinks.utils.Route
import com.example.drinks.utils.UiEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddDetailsScreen(
    onNavigate: (UiEvent.Navigate) -> Unit, viewModel: AddViewModel = hiltViewModel()
) {
    var cocktailState by remember {
        mutableStateOf(viewModel.cocktail)
    }
    var cocktailNameState by remember {
        mutableStateOf("")
    }
    var cocktailImageState by remember {
        mutableStateOf<Uri?>(null)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier
            .size(200.dp)
            .clip(
                RoundedCornerShape(20.dp)
            )
            .clickable {
                //TODO PICK IMAGE FROM GALLAREY
                //TODO SAVE VALUE TO cocktailImageState
            }) {
            Image(
                painter = painterResource(id = R.drawable.img_1),
                contentDescription = "",
                Modifier
                    .clip(
                        RoundedCornerShape(20.dp)
                    )
                    .aspectRatio(1f)
                    .size(200.dp)

            )
            Icon(
                painter = painterResource(id = R.drawable.baseline_add_circle_24),
                modifier = Modifier.size(35.dp),
                contentDescription = ""
            )
        }

        MainEditText(value = cocktailNameState, label = "cocktail name", onTextChanged = {
            cocktailNameState = it
        })

        MainButton(
            text = "Save"
        ) {
            cocktailState?.name = cocktailNameState
            cocktailState?.image = cocktailImageState

            //TODO SAVE COCKTAIL
            onNavigate(UiEvent.Navigate(Route.Drinks.route))
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainEditText(
    modifier: Modifier = Modifier, value: String, label: String, onTextChanged: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onTextChanged,
        label = { Text(text = label) },
        maxLines = 1,
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
    )
}