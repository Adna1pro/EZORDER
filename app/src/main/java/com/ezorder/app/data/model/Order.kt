package com.ezorder.app.data.model

enum class OrderStatus {
    PLACED, CONFIRMED, PREPARING, READY, COMPLETED, CANCELLED
}

/**
 * totalAmount is stored, not computed from items — an order is a receipt
 * of what was agreed at that moment, so it shouldn't drift if menu
 * prices change afterward. reservationId is null for a walk-in/pickup
 * order not tied to a table booking.
 */
data class Order(
    val id: String,
    val restaurantId: String,
    val userId: String,
    val reservationId: String? = null,
    val items: List<CartItem>,
    val totalAmount: Double,
    val status: OrderStatus,
    val placedAt: String,
    val estimatedReadyTime: String? = null
)