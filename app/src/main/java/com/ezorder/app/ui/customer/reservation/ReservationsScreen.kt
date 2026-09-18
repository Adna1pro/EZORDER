package com.ezorder.app.ui.customer.reservation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ezorder.app.ui.components.EmptyState

@Composable
fun ReservationsScreen(modifier: Modifier = Modifier) {
    EmptyState(
        title = "No reservations yet",
        message = "Book a table and pre-order food for your arrival time.",
        icon = Icons.Filled.DateRange,
        modifier = modifier.fillMaxSize()
    )
}