package com.example.drinks.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RightButton(
    modifier: Modifier = Modifier,
    text: String,
    onclick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        MainButton(text = text, modifier = Modifier.align(Alignment.BottomEnd), onclick = onclick)

    }
}