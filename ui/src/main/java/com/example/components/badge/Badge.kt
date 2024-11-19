package com.example.components.badge

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun Badge(value: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(
                color = Color.Green.copy(
                    alpha = 0.3f
                ),
                shape = RoundedCornerShape(size = 50.dp)
            )
            .padding(horizontal = 8.dp)
    ) {
        Text(
            text = value,
            textAlign = TextAlign.Center,
            style = TextStyle.Default.copy(
                color = Color.White,
                fontWeight = FontWeight.ExtraBold
            )
        )
    }
}