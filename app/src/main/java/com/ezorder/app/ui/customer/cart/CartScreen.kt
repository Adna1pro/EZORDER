package com.ezorder.app.ui.customer.cart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ezorder.app.data.model.CartItem
import com.ezorder.app.data.repository.CartRepository
import com.ezorder.app.ui.components.EmptyState
import com.ezorder.app.ui.theme.LocalEZSpacing

@Composable
fun CartScreen(
    cartRepository: CartRepository,
    modifier: Modifier = Modifier
) {
    val cart by cartRepository.cart.collectAsState()
    val spacing = LocalEZSpacing.current

    if (cart.items.isEmpty()) {
        EmptyState(
            title = "Your cart is empty",
            message = "Add items from a restaurant's menu to get started.",
            modifier = modifier.fillMaxSize()
        )
        return
    }

    Column(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(spacing.md)
        ) {
            items(cart.items, key = { it.foodItemId }) { item ->
                CartRow(
                    item = item,
                    onRemove = { cartRepository.removeItem(item.foodItemId) }
                )
                HorizontalDivider()
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(spacing.md),
            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween
        ) {
            Text("Subtotal", style = MaterialTheme.typography.titleMedium)
            Text("₹${"%.2f".format(cart.subtotal)}", style = MaterialTheme.typography.titleMedium)
        }
    }
}

@Composable
private fun CartRow(item: CartItem, onRemove: () -> Unit) {
    val spacing = LocalEZSpacing.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = spacing.sm),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(item.name, style = MaterialTheme.typography.bodyLarge)
            Text(
                "Qty ${item.quantity} · ₹${"%.2f".format(item.price * item.quantity)}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        IconButton(onClick = onRemove) {
            Icon(Icons.Filled.Delete, contentDescription = "Remove ${item.name}")
        }
    }
}