package com.ezorder.app.data.model

enum class TableStatus {
    AVAILABLE, RESERVED, OCCUPIED
}

data class Table(
    val id: String,
    val restaurantId: String,
    val tableNumber: Int,
    val capacity: Int,
    val status: TableStatus
)