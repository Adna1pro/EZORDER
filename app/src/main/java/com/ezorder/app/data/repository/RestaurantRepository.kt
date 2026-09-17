package com.ezorder.app.data.repository

import com.ezorder.app.data.model.Restaurant

interface RestaurantRepository {
    suspend fun getAllRestaurants(): List<Restaurant>
    suspend fun getRestaurantById(id: String): Restaurant?
}

class MockRestaurantRepository : RestaurantRepository {
    private val restaurants = listOf(
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
        ),
        Restaurant(
            id = "r3", name = "Coastal Curry House",
            imageUrl = "https://images.unsplash.com/photo-1559847844-5315695dadae",
            cuisine = listOf("Seafood", "Coastal"), priceLevel = 3,
            avgRating = 4.7, reviewCount = 501,
            latitude = 13.0067, longitude = 80.2570,
            isOpen = false, openingTime = "12:00", closingTime = "22:00",
            address = "8 Besant Nagar, Chennai", phoneNumber = "+91 9876543212"
        ),
        Restaurant(
            id = "r4", name = "Urban Tandoor",
            imageUrl = "https://images.unsplash.com/photo-1600891964092-4316c288032e",
            cuisine = listOf("North Indian", "Grill"), priceLevel = 3,
            avgRating = 4.2, reviewCount = 156,
            latitude = 13.0358, longitude = 80.2297,
            isOpen = true, openingTime = "12:00", closingTime = "23:30",
            address = "22 Nungambakkam High Rd, Chennai", phoneNumber = "+91 9876543213"
        ),
        Restaurant(
            id = "r5", name = "The Dosa Corner",
            imageUrl = "https://images.unsplash.com/photo-1630383249896-424e482df921",
            cuisine = listOf("South Indian", "Breakfast"), priceLevel = 1,
            avgRating = 4.6, reviewCount = 890,
            latitude = 13.0418, longitude = 80.2337,
            isOpen = true, openingTime = "07:00", closingTime = "21:00",
            address = "3 T Nagar Main Rd, Chennai", phoneNumber = "+91 9876543214"
        )
    )

    override suspend fun getAllRestaurants(): List<Restaurant> = restaurants
    override suspend fun getRestaurantById(id: String): Restaurant? =
        restaurants.find { it.id == id }
}