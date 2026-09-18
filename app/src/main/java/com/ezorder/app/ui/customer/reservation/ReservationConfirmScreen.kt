package com.ezorder.app.ui.customer.reservation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ezorder.app.ui.theme.LocalEZSpacing

@Composable
fun ReservationConfirmScreen(
    restaurantId: String,
    tableId: String,
    modifier: Modifier = Modifier,
    onConfirmed: () -> Unit = {},
    viewModel: ReservationConfirmViewModel = viewModel()
) {
    val spacing = LocalEZSpacing.current
    val confirmed by viewModel.confirmedReservation.collectAsState()
    var partySize by remember { mutableIntStateOf(2) }

    if (confirmed != null) {
        Column(
            modifier = modifier.fillMaxSize().padding(spacing.md)
        ) {
            Text("Reservation confirmed!", style = MaterialTheme.typography.headlineSmall)
            Text(
                "Table booked for ${confirmed!!.partySize} at ${confirmed!!.reservationDateTime}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
        return
    }

    Column(modifier = modifier.fillMaxSize().padding(spacing.md)) {
        Text("Confirm Reservation", style = MaterialTheme.typography.headlineSmall)

        Text(
            "Note: date/time is temporarily fixed until the picker is built.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(top = spacing.sm, bottom = spacing.md)
        )

        OutlinedTextField(
            value = partySize.toString(),
            onValueChange = { it.toIntOrNull()?.let { n -> partySize = n } },
            label = { Text("Party size") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                viewModel.confirmReservation(restaurantId, tableId, partySize)
                onConfirmed()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = spacing.md)
        ) {
            Text("Confirm Reservation")
        }
    }
}