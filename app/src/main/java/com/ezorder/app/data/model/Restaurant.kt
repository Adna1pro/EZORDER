package com.ezorder.app.data.model

/**
 * Raw restaurant data as it would come from a backend.
 * Screens compute anything user-relative (distance, formatted rating)
 * rather than storing it here.
 */
data class Restaurant(
    val id: String,
    val name: String,
    val imageUrl: String,
    val cuisine: List<String>,
    val priceLevel: Int,
    val avgRating: Double,
    val reviewCount: Int,
    val latitude: Double,
    val longitude: Double,
    val isOpen: Boolean,
    val openingTime: String,
    val closingTime: String,
    val address: String,
    val phoneNumber: String
)