package com.ezorder.app.data.model

/**
 * A single menu item belonging to a restaurant.
 */
data class FoodItem(
    val id: String,
    val restaurantId: String,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String,
    val isVegetarian: Boolean,
    val isAvailable: Boolean,
    val isBestseller: Boolean,
    val category: String
)