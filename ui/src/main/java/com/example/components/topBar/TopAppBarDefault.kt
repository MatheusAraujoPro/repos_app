package com.example.components.topBar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.components.bottomBar.BottomBarScreens
import com.example.ui.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarDefault(
    handleBarVisibility: () -> Unit?,
    text: String,
    isSearch: Boolean,
    handleSearchRepo: (repoName: String) -> Unit?,
    route: String
) {
    val isRouteList = adjustTitle(route) == BottomBarScreens.List.title
    TopAppBar(
        modifier = Modifier
            .height(68.dp)
            .fillMaxWidth(),
        actions = {
            if (isSearch)
                SearchBar(
                    handleBarVisibility = handleBarVisibility,
                    text = text,
                    handleSearchRepo = handleSearchRepo
                )
            else {
                if (isRouteList)
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(6.dp)
                            .clickable {
                                handleBarVisibility()
                            }
                    )
            }
        },
        title = {
            if (isSearch.not())
                Text(
                    text = adjustTitle(route),
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth()
                )
        },
        windowInsets = WindowInsets(
            top = 16.dp
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchBar(
    text: String,
    handleSearchRepo: (repoName: String) -> Unit?,
    handleBarVisibility: () -> Unit?,
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.background(
            color = Color.White,
            shape = RoundedCornerShape(8.dp)
        )
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = stringResource(R.string.button_go_back_text),
            modifier = Modifier.clickable {
                handleBarVisibility()
            },
        )
        Spacer(modifier = Modifier.width(8.dp))
        TextField(
            value = text,
            modifier = Modifier
                .fillMaxWidth(
                    fraction = 0.955f
                ),
            placeholder = {
                Text(text = stringResource(R.string.text_find_placeholder))
            },
            onValueChange = { value ->
                handleSearchRepo(value)
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                textColor = Color.Black
            ),
            shape = RoundedCornerShape(8.dp),
            maxLines = 1,
        )
    }
}

private fun adjustTitle(route: String): String {
    return when (route) {
        "list" -> BottomBarScreens.List.title
        "bookmark" -> BottomBarScreens.Favorite.title
        else -> ""
    }
}