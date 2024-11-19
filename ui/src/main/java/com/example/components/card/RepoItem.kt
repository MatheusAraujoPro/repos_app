package com.example.components.card

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.components.badge.Badge
import com.example.components.bottomBar.BottomBarScreens
import com.example.ui.R

@Composable
fun RepoItem(
    description: String,
    language: String,
    name: String,
    imageURl: String,
    onClick: () -> Unit,
    isRepoSaved: Boolean,
    route: String
) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()

    ) {
        Column {
            ImageTec(
                language = if (imageURl.isEmpty()
                        .not()
                ) imageURl else stringResource(
                    R.string.repo_no_language
                )
            )
            Content(
                description = description,
                language = language,
                name = name,
                onClick = onClick,
                isRepoSaved = isRepoSaved,
                route = route
            )
        }
    }
}

@Composable
fun ImageTec(language: String) {
    AsyncImage(
        modifier = Modifier
            .fillMaxWidth(),

        model = language,
        contentDescription = "Teste da Lib"
    )
}

@Composable
fun Title(text: String, value: String) {
    Column(
        modifier = Modifier.fillMaxWidth(fraction = 0.6f)
    ) {
        Text(
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            text = text
        )
        Text(
            fontWeight = FontWeight.Light,
            fontSize = 24.sp,
            text = value,
        )
    }
}

@Composable
fun Description(value: String) {
    Text(
        fontWeight = FontWeight.Light,
        fontSize = 18.sp,
        text = value,
    )
}

@Composable
fun Content(
    description: String,
    language: String,
    name: String,
    onClick: () -> Unit,
    isRepoSaved: Boolean,
    route: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(14.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Title(
                text = stringResource(R.string.repo_name),
                value = name
            )
            DecideIcon(
                route = route,
                onClick = { onClick.invoke() },
                isRepoSaved = isRepoSaved
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Description(value = description)
        Spacer(modifier = Modifier.height(8.dp))
        if (language.isNotBlank())
            Badge(value = language)
    }
}

@Composable
fun DecideIcon(
    route: String,
    onClick: () -> Unit,
    isRepoSaved: Boolean,
) {
    if (route == BottomBarScreens.List.route)
        Icon(
            imageVector = Icons.Outlined.Star,
            contentDescription = null,
            modifier = Modifier
                .clickable {
                    onClick.invoke()
                }
                .defaultMinSize(
                    minWidth = 32.dp,
                    minHeight = 32.dp
                ),
            tint = if (isRepoSaved)
                Color.Yellow
            else
                Color.Black
        )
    else
        Icon(
            imageVector = Icons.Outlined.Delete,
            contentDescription = null,
            modifier = Modifier
                .clickable {
                    onClick.invoke()
                }
                .defaultMinSize(
                    minWidth = 32.dp,
                    minHeight = 32.dp
                ),
            tint = Color.Black
        )
}


@Composable
@Preview
fun CardPreview() {
    val list = mutableListOf(1, 2, 3)
    Surface {
        LazyColumn {
            items(list) {
                RepoItem(
                    description = "",
                    language = "",
                    name = "",
                    imageURl = "",
                    onClick = {},
                    isRepoSaved = false,
                    route = ""
                )
            }
        }
    }
}