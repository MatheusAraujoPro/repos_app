package com.example.components.bottomBar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BottomBar(navigate: (route: String) -> Unit) {
    val screens = listOf(
        BottomBarScreens.List,
        BottomBarScreens.Favorite
    )

    Row(
        modifier = Modifier
            .padding(top = 8.dp)
            .background(White)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                navigate = navigate
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreens,
    navigate: (route: String) -> Unit
) {
    Box(
        modifier = Modifier
            .background(
                color = White,
                shape = CircleShape
            )
//            .height(52.dp)
            .clickable(onClick = {
                navigate.invoke(screen.route)
            }),
    ) {
        Column(
            modifier = Modifier
                .background(
                    color = White,
                    shape = CircleShape
                )
                .padding(start = 10.dp, end = 10.dp, top = 8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = screen.icon!!,
                contentDescription = "icon",
            )
            AnimatedVisibility(visible = true) {
                Text(
                    text = screen.title,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
