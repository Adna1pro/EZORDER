package com.ezorder.app.ui.customer.orders

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ezorder.app.ui.components.EmptyState

@Composable
fun OrdersScreen(modifier: Modifier = Modifier) {
    EmptyState(
        title = "No orders yet",
        message = "Your order history and active orders will show up here.",
        icon = Icons.Filled.ShoppingCart,
        modifier = modifier.fillMaxSize()
    )
}