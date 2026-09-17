package com.ezorder.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ezorder.app.ui.theme.EZORDERTheme
import com.ezorder.app.ui.theme.LocalEZSpacing

/**
 * A single filter/category pill. Used in horizontally-scrollable rows
 * on Home ("Biriyani", "Pizza") and Menu ("Starters", "Biryani").
 */
@Composable
fun CategoryChip(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val spacing = LocalEZSpacing.current

    Row(
        modifier = modifier
            .clip(MaterialTheme.shapes.extraLarge)
            .background(
                if (selected) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.surface
            )
            .border(
                width = 1.dp,
                color = if (selected) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.outline,
                shape = MaterialTheme.shapes.extraLarge
            )
            .clickable(onClick = onClick)
            .padding(horizontal = spacing.md, vertical = spacing.sm)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge,
            color = if (selected) MaterialTheme.colorScheme.onPrimary
            else MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

/**
 * Search entry point. Can act as a tap-to-navigate trigger (pass only onClick)
 * or, later, as a live input field (pass value + onValueChange too).
 */
@Composable
fun EZSearchBar(
    placeholder: String = "Search restaurants, dishes or cuisines",
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val spacing = LocalEZSpacing.current

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
            .clickable(onClick = onClick)
            .padding(horizontal = spacing.md, vertical = spacing.md),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(spacing.sm)
    ) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = placeholder,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

/**
 * Small star + rating pill, used inside RestaurantCard, Restaurant Details, and Reviews.
 */
@Composable
fun RatingBadge(
    rating: Double,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Filled.Star,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.height(16.dp)
        )
        Text(
            text = " $rating",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SmallComponentsPreview() {
    EZORDERTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            EZSearchBar(onClick = {})

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                CategoryChip(label = "Biriyani", selected = true, onClick = {})
                CategoryChip(label = "Pizza", selected = false, onClick = {})
                CategoryChip(label = "South Indian", selected = false, onClick = {})
            }

            RatingBadge(rating = 4.5)
        }
    }
}