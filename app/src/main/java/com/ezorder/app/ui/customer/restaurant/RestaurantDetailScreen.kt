package com.ezorder.app.ui.customer.restaurant

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import coil.compose.AsyncImage
import com.ezorder.app.ui.components.EmptyState
import com.ezorder.app.ui.components.LoadingState
import com.ezorder.app.ui.theme.LocalEZSpacing

/**
 * Read-only restaurant detail view. No actions yet — "Reserve a Table"
 * and "View Menu" buttons come in a later step once those flows exist.
 */
@Composable
fun RestaurantDetailScreen(
    restaurantId: String,
    modifier: Modifier = Modifier,
    onViewMenuClick: (String) -> Unit = {},
    onReserveTableClick: (String) -> Unit = {},
    viewModel: RestaurantDetailViewModel = viewModel(
        factory = viewModelFactory {
            initializer { RestaurantDetailViewModel(restaurantId) }
        }
    )
) {
    val restaurant by viewModel.restaurant.collectAsState()
    val spacing = LocalEZSpacing.current

    when (val current = restaurant) {
        null -> LoadingState(modifier = modifier.fillMaxSize())
        else -> {
            LazyColumn(modifier = modifier.fillMaxSize()) {
                item {
                    AsyncImage(
                        model = current.imageUrl,
                        contentDescription = current.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(220.dp)
                    )
                }
                item {
                    Column(modifier = Modifier.padding(spacing.md)) {
                        Text(
                            text = current.name,
                            style = MaterialTheme.typography.headlineSmall
                        )
                        Text(
                            text = current.cuisine.joinToString(" • "),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = "★ ${current.avgRating} (${current.reviewCount} reviews)  •  " +
                                    "₹".repeat(current.priceLevel),
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = if (current.isOpen) "Open now" else "Closed",
                            style = MaterialTheme.typography.bodyMedium,
                            color = if (current.isOpen)
                                MaterialTheme.colorScheme.primary
                            else
                                MaterialTheme.colorScheme.error
                        )
                        Text(
                            text = "${current.openingTime} – ${current.closingTime}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = current.address,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(top = spacing.sm)
                        )
                        Text(
                            text = current.phoneNumber,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Button(
                            onClick = { onViewMenuClick(restaurantId) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = spacing.md)
                        ) {
                            Text("View Menu")
                        }

                        androidx.compose.material3.OutlinedButton(
                            onClick = { onReserveTableClick(restaurantId) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = spacing.sm)
                        ) {
                            Text("Reserve a Table")
                        }
                    }
                }
            }
        }
    }
}