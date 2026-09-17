package com.ezorder.app.data.model

enum class ReservationStatus {
    PENDING, CONFIRMED, CANCELLED, COMPLETED
}

/**
 * preOrderedItems is what makes EZORDER EZORDER: a reservation can
 * optionally carry a pre-order so food is ready around arrival time.
 * Empty list = a normal table booking with no pre-order.
 */
data class Reservation(
    val id: String,
    val restaurantId: String,
    val tableId: String,
    val userId: String,
    val partySize: Int,
    val reservationDateTime: String,   // e.g. "2026-09-20T19:30"
    val status: ReservationStatus,
    val preOrderedItems: List<CartItem> = emptyList(),
    val specialRequests: String = ""
)