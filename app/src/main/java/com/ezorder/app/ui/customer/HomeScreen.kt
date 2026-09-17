package com.ezorder.app.ui.customer

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ezorder.app.data.model.Restaurant
import com.ezorder.app.ui.components.CustomerTab
import com.ezorder.app.ui.components.EZBottomNav
import com.ezorder.app.ui.components.EmptyState
import com.ezorder.app.ui.components.LoadingState
import com.ezorder.app.ui.components.RestaurantCard
import com.ezorder.app.ui.theme.EZORDERTheme
import com.ezorder.app.ui.theme.LocalEZSpacing

/**
 * The customer Home screen. No navigation wiring yet — tapping a
 * RestaurantCard or a bottom nav tab does nothing until a later step.
 * The point of this pass is: real mock data renders on a real screen.
 */
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel()
) {
    val restaurants by viewModel.restaurants.collectAsState()

    // Local-only for now — no NavController exists yet, so this just
    // tracks which tab looks selected. Wiring real navigation is a
    // later Phase 3 step.
    var selectedTab by remember { mutableStateOf(CustomerTab.Home) }

    Scaffold(
        bottomBar = {
            EZBottomNav(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it }
            )
        }
    ) { innerPadding ->
        HomeContent(
            restaurants = restaurants,
            contentPadding = innerPadding,
            modifier = Modifier.fillMaxSize()
        )
    }
}

/**
 * Separated from HomeScreen so it can be previewed with fake data
 * without needing a real ViewModel or Scaffold in the preview.
 */
@Composable
private fun HomeContent(
    restaurants: List<Restaurant>,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    val spacing = LocalEZSpacing.current

    when {
        // MockRestaurantRepository always returns a non-empty list
        // immediately, so this branch won't show in practice yet —
        // but it's here correctly for when a real fetch can be empty.
        restaurants.isEmpty() -> {
            EmptyState(
                title = "No restaurants nearby",
                message = "Try widening your search area or check back later.",
                modifier = modifier.padding(contentPadding)
            )
        }
        else -> {
            LazyColumn(
                modifier = modifier.padding(contentPadding),
                contentPadding = PaddingValues(spacing.md),
            ) {
                items(restaurants, key = { it.id }) { restaurant ->
                    RestaurantCard(
                        name = restaurant.name,
                        imageUrl = restaurant.imageUrl,
                        rating = restaurant.avgRating,
                        cuisine = restaurant.cuisine.joinToString(" • "),
                        priceLevel = "₹".repeat(restaurant.priceLevel),
                        distanceKm = 1.2, // placeholder — real distance calc comes with location, later
                        isOpen = restaurant.isOpen,
                        onClick = { /* navigation comes in a later step */ },
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
        val sampleRestaurants = listOf(
            Restaurant(
                id = "r1", name = "Madras Bowl",
                imageUrl = "https://images.unsplash.com/photo-1517248135467-4c7edcad34c4",
                cuisine = listOf("South Indian", "Biryani"), priceLevel = 2,
                avgRating = 4.5, reviewCount = 342,
                latitude = 13.0827, longitude = 80.2707,
                isOpen = true, openingTime = "10:00", closingTime = "22:30",
                address = "12 Cathedral Rd, Chennai", phoneNumber = "+91 9876543210"
            ),
            Restaurant(
                id = "r2", name = "Punjabi Tadka",
                imageUrl = "https://images.unsplash.com/photo-1517093602136-e6b7ca9c1a8b",
                cuisine = listOf("North Indian", "Punjabi"), priceLevel = 2,
                avgRating = 4.3, reviewCount = 210,
                latitude = 13.0500, longitude = 80.2121,
                isOpen = true, openingTime = "11:00", closingTime = "23:00",
                address = "45 Anna Salai, Chennai", phoneNumber = "+91 9876543211"
            )
        )
        HomeContent(
            restaurants = sampleRestaurants,
            contentPadding = PaddingValues(0.dp)
        )
    }
}