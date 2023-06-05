package com.example.drinks.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ValidateText(
    validCocktail :Boolean ,
    buttonClicked :Boolean ,
    text:String
){
    if (!validCocktail && buttonClicked) {
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = text,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Red
        )
        Spacer(modifier = Modifier.height(10.dp))
    }
}