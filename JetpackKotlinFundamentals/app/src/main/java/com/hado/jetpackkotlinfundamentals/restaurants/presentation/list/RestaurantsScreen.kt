package com.hado.jetpackkotlinfundamentals.restaurants.presentation.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hado.jetpackkotlinfundamentals.restaurants.domain.Restaurant

@Preview(
    name = "Restaurant App",
    device = Devices.PIXEL_3,
    showSystemUi = true
)
@Composable
fun RestaurantScreen(
    state: RestaurantsScreenState,
    onItemClick: (id: Int) -> Unit = {},
    onFavoriteClick: (id: Int, oldValue: Boolean) -> Unit
) {
//    Column(Modifier.verticalScroll(rememberScrollState())) {
//        dummyRestaurants.forEach { restaurant ->
//            RestaurantItem(restaurant)
//        }
//    }

//    val viewModel: RestaurantsViewModel = viewModel()
    // when configuration changes (orientation change)
    // we lost all of favorited items, we can use rememberSaveable
    // or using ViewModel above
//    val state: MutableState<List<Restaurant>> = remember {
//        mutableStateOf(viewModel.getRestaurants())
//    }
//    val state: MutableState<List<Restaurant>> = rememberSaveable {
//        mutableStateOf(viewModel.getRestaurants())
//    }
    // Prevent multiple redundant API calls due to re-composition
//    LaunchedEffect(key1 = "request_restaurants") {
//        viewModel.getRestaurants()
//    }

//    val restaurants = viewModel.state.value
//    val isLoading = restaurants.isEmpty()
//    val state = viewModel.state.value
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            contentPadding = PaddingValues(
                vertical = 8.dp,
                horizontal = 8.dp
            )
        ) {
//        items(state.value) { restaurant ->
            items(state.restaurants) { restaurant ->
                RestaurantItem(
                    item = restaurant,
                    onFavoriteClick = { id, oldValue ->
//                        viewModel.toggleFavorite(id, oldValue)
                          onFavoriteClick(id, oldValue)
                    },
                    onItemClick = { id -> onItemClick(id) }
                )
            }
        }

        if (state.isLoading) CircularProgressIndicator()

        if (state.error != null) Text(state.error)
    }
}
@Composable
fun RestaurantItem(
    item: Restaurant,
    onFavoriteClick: (id: Int, oldValue: Boolean) -> Unit,
    onItemClick: (id: Int) -> Unit
) {
    val icon = if (item.isFavorite)
        Icons.Filled.Favorite
    else
        Icons.Filled.FavoriteBorder
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                onItemClick(item.id)
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            RestaurantIcon(
                Icons.Filled.Place,
                Modifier.weight(0.15f)
            )
            RestaurantDetails(
                item.title,
                item.description,
                Modifier.weight(0.7f)
            )
            RestaurantIcon(icon, Modifier.weight(0.15f)) {
                onFavoriteClick(item.id, item.isFavorite)
            }
        }
    }
}

@Composable
fun RestaurantIcon(
    icon: ImageVector,
    modifier: Modifier,
    onClick: () -> Unit = {}
) {
    Image(
        imageVector = icon,
        contentDescription = "Restaurant icon",
        modifier = modifier
            .padding(8.dp)
            .clickable {
                onClick()
            }
    )
}

@Composable
fun RestaurantDetails(
    title: String,
    description: String,
    modifier: Modifier,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start
) {
    Column(modifier = modifier, horizontalAlignment = horizontalAlignment) {
        Text(title, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
        CompositionLocalProvider(LocalContentColor provides LocalContentColor.current.copy(alpha = 0.8f)) {
            Text(description, style = MaterialTheme.typography.bodyMedium)
        }
    }
}