package com.ezorder.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ezorder.app.ui.theme.EZORDERTheme
import com.ezorder.app.ui.theme.EZSuccess
import com.ezorder.app.ui.theme.LocalEZSpacing

/**
 * The primary restaurant preview card, used in Home, Search, and list screens.
 * Placeholder parameters here — will map directly to the Restaurant model in Phase 2.
 */
@Composable
fun RestaurantCard(
    name: String,
    imageUrl: String,
    rating: Double,
    cuisine: String,
    priceLevel: String,      // e.g. "₹₹"
    distanceKm: Double,
    isOpen: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val spacing = LocalEZSpacing.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.large)
            .background(MaterialTheme.colorScheme.surface)
            .clickable(onClick = onClick)
    ) {
        // Image with open/closed badge overlay
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
                contentScale = ContentScale.Crop
            )

            // Status badge, top-left
            Row(
                modifier = Modifier
                    .padding(spacing.sm)
                    .clip(MaterialTheme.shapes.extraSmall)
                    .background(
                        if (isOpen) EZSuccess.copy(alpha = 0.95f)
                        else MaterialTheme.colorScheme.error.copy(alpha = 0.95f)
                    )
                    .padding(horizontal = spacing.sm, vertical = 4.dp),
            ) {
                Text(
                    text = if (isOpen) "Open" else "Closed",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.White
                )
            }
        }

        Column(
            modifier = Modifier.padding(spacing.md)
        ) {
            // Name + rating row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                RatingBadge(rating = rating)
            }

            Spacer(Modifier.height(4.dp))

            // Cuisine • price
            Text(
                text = "$cuisine • $priceLevel",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(Modifier.height(4.dp))

            // Distance
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Filled.LocationOn,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.height(14.dp)
                )
                Text(
                    text = " ${distanceKm} km away",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RestaurantCardPreview() {
    EZORDERTheme {
        RestaurantCard(
            name = "Madras Bowl",
            imageUrl = "https://images.unsplash.com/photo-1517248135467-4c7edcad34c4",
            rating = 4.5,
            cuisine = "South Indian • Biryani",
            priceLevel = "₹₹",
            distanceKm = 1.2,
            isOpen = true,
            onClick = {},
            modifier = Modifier.padding(16.dp)
        )
    }
}