package com.example.drinks.ui.add

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.drinks.R
import com.example.drinks.ui.components.RightButton
import com.example.drinks.ui.components.ValidateText
import com.example.drinks.utils.Route
import com.example.drinks.utils.UiEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddDetailsScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: AddViewModel = hiltViewModel()
) {
    val cocktailState by remember {
        viewModel.cocktailState
    }
    var cocktailNameState by remember {
        mutableStateOf("")
    }
    var selectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    var validCocktail by remember {
        mutableStateOf(false)
    }

    var buttonClicked by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = cocktailState, block ={
        Log.d("TAG", "AddDetailsScreen: ${cocktailState.drinks}")
    } )
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        ImagePicker(uri = selectedImageUri, selectedImageUri = {
            selectedImageUri = it
        })

        MainEditText(value = cocktailNameState, label = "cocktail name", onTextChanged = {
            cocktailNameState = it
        })

        ValidateText(
            validCocktail = validCocktail,
            buttonClicked = buttonClicked,
            text = "required fields"
        )

        RightButton(text = "Save") {
            buttonClicked = true
            cocktailState.name = cocktailNameState
            cocktailState.image = selectedImageUri

            if (cocktailNameState.isNotEmpty() && selectedImageUri != null) {
                validCocktail = true
                viewModel.addNewCocktail(cocktailState)
                onNavigate(UiEvent.Navigate(Route.Drinks.route))
            } else {
                validCocktail = false
            }
        }
    }

}

@Composable
fun ImagePicker(
    uri :Uri?,
    selectedImageUri: (Uri?) -> Unit,
    modifier: Modifier = Modifier
) {

    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { selectedImageUri(it) }
    )

    Box(contentAlignment = Alignment.Center, modifier = modifier
        .size(200.dp)
        .clip(
            RoundedCornerShape(20.dp)
        )
        .background(Color.LightGray)
        .clickable {
            singlePhotoPickerLauncher.launch(
                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
            )
        }) {


        val painter = rememberAsyncImagePainter(
            model = uri,
        )
        Image(
            painter = painter,
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