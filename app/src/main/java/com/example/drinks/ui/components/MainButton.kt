package com.example.drinks.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainButton(
    modifier: Modifier = Modifier,
    text: String,
    onclick: () -> Unit
) {
    Button(
        onClick = { onclick() },
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = modifier.padding(10.dp),
        shape = RoundedCornerShape(100.dp)
    ) {
        Text(text = text, fontWeight = FontWeight.Medium, fontSize = 18.sp )
    }
}