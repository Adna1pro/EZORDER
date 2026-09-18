package com.ezorder.app.ui.customer.menu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.ezorder.app.data.repository.CartRepository
import com.ezorder.app.ui.components.EmptyState
import com.ezorder.app.ui.components.FoodCard
import com.ezorder.app.ui.theme.LocalEZSpacing

@Composable
fun MenuScreen(
    restaurantId: String,
    cartRepository: CartRepository,
    modifier: Modifier = Modifier,
    onCartClick: () -> Unit = {},
    viewModel: MenuViewModel = viewModel(
        factory = viewModelFactory {
            initializer { MenuViewModel(restaurantId, cartRepository) }
        }
    )
) {
    val items by viewModel.items.collectAsState()
    val cart by cartRepository.cart.collectAsState()
    val spacing = LocalEZSpacing.current

    Column(modifier = modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(spacing.md),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(onClick = onCartClick) {
                // Badge shows live item count so the customer gets confirmation
                // an item was added without needing to open the cart screen.
                if (cart.itemCount > 0) {
                    BadgedBox(
                        badge = {
                            Badge(
                                containerColor = MaterialTheme.colorScheme.error,
                                contentColor = MaterialTheme.colorScheme.onError
                            ) {
                                Text(if (cart.itemCount > 9) "9+" else cart.itemCount.toString())
                            }
                        }
                    ) {
                        Icon(
                            Icons.Filled.ShoppingCart,
                            contentDescription = "Cart (${cart.itemCount} items)"
                        )
                    }
                } else {
                    Icon(Icons.Filled.ShoppingCart, contentDescription = "Cart")
                }
            }
        }

        when {
            items.isEmpty() -> {
                EmptyState(
                    title = "Menu unavailable",
                    message = "This restaurant hasn't added menu items yet.",
                    modifier = Modifier.fillMaxSize()
                )
            }
            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(spacing.md)
                ) {
                    items(items, key = { it.id }) { item ->
                        FoodCard(
                            name = item.name,
                            description = item.description,
                            price = "₹${item.price}",
                            imageUrl = item.imageUrl,
                            isVegetarian = item.isVegetarian,
                            isAvailable = item.isAvailable,
                            isBestseller = item.isBestseller,
                            onAddClick = { viewModel.addToCart(item) },
                            onClick = { /* food detail screen doesn't exist yet */ },
                            modifier = Modifier.padding(bottom = spacing.md)
                        )
                    }
                }
            }
        }
    }
}