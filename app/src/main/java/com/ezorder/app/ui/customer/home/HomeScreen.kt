package com.ezorder.app.ui.customer.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ezorder.app.ui.components.EmptyState
import com.ezorder.app.ui.components.RestaurantCard
import com.ezorder.app.ui.theme.EZORDERTheme
import com.ezorder.app.ui.theme.LocalEZSpacing

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
    modifier: Modifier = Modifier,
    onRestaurantClick: (String) -> Unit = {}
) {
    val restaurants by viewModel.restaurants.collectAsState()
    val spacing = LocalEZSpacing.current

    when {
        restaurants.isEmpty() -> {
            EmptyState(
                title = "No restaurants nearby",
                message = "Try widening your search area or check back later.",
                modifier = modifier.fillMaxSize()
            )
        }
        else -> {
            LazyColumn(
                modifier = modifier.fillMaxSize(),
                contentPadding = PaddingValues(spacing.md),
            ) {
                items(restaurants, key = { it.id }) { restaurant ->
                    RestaurantCard(
                        name = restaurant.name,
                        imageUrl = restaurant.imageUrl,
                        rating = restaurant.avgRating,
                        cuisine = restaurant.cuisine.joinToString(" • "),
                        priceLevel = "₹".repeat(restaurant.priceLevel),
                        distanceKm = 1.2,
                        isOpen = restaurant.isOpen,
                        onClick = { onRestaurantClick(restaurant.id) },
                        modifier = Modifier.padding(bottom = spacing.md)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, heightDp = 800)
@Composable
private fun HomeScreenPreview() {
    EZORDERTheme {
        EmptyState(
            title = "No restaurants nearby",
            message = "Try widening your search area or check back later.",
        )
    }
}