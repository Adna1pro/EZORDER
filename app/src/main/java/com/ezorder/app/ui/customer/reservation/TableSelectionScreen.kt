package com.ezorder.app.ui.customer.reservation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.ezorder.app.data.model.Table
import com.ezorder.app.ui.components.EmptyState
import com.ezorder.app.ui.theme.LocalEZSpacing

/**
 * First pass: pick a table only. Date/time selection and attaching
 * pre-ordered cart items are separate, later steps.
 */
@Composable
fun TableSelectionScreen(
    restaurantId: String,
    modifier: Modifier = Modifier,
    onTableSelected: (Table) -> Unit = {},
    viewModel: TableSelectionViewModel = viewModel(
        factory = viewModelFactory {
            initializer { TableSelectionViewModel(restaurantId) }
        }
    )
) {
    val tables by viewModel.availableTables.collectAsState()
    val spacing = LocalEZSpacing.current

    if (tables.isEmpty()) {
        EmptyState(
            title = "No tables available",
            message = "This restaurant has no open tables right now.",
            modifier = modifier.fillMaxSize()
        )
        return
    }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(spacing.md)
    ) {
        items(tables, key = { it.id }) { table ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = spacing.md),
                onClick = { onTableSelected(table) }
            ) {
                androidx.compose.foundation.layout.Column(modifier = Modifier.padding(spacing.md)) {
                    Text(
                        text = "Table ${table.tableNumber}",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "Seats ${table.capacity}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}