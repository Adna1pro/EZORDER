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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
 * A small green/red square indicating vegetarian or non-vegetarian,
 * following the standard Indian food-app convention.
 */
@Composable
fun VegIndicator(isVegetarian: Boolean, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(14.dp)
            .background(Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .size(14.dp)
                .padding(1.dp)
                .background(
                    color = if (isVegetarian) EZSuccess else MaterialTheme.colorScheme.error,
                    shape = MaterialTheme.shapes.extraSmall
                )
        )
    }
}

/**
 * A compact horizontal menu item row.
 * Placeholder parameters here — will map directly to the FoodItem model in Phase 2.
 */
@Composable
fun FoodCard(
    name: String,
    description: String,
    price: String,           // pre-formatted, e.g. "₹220"
    imageUrl: String,
    isVegetarian: Boolean,
    isAvailable: Boolean,
    isBestseller: Boolean = false,
    onAddClick: () -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val spacing = LocalEZSpacing.current

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(enabled = isAvailable, onClick = onClick)
            .padding(vertical = spacing.sm),
        horizontalArrangement = Arrangement.spacedBy(spacing.md)
    ) {
        // Text content
        Column(
            modifier = Modifier.weight(1f)
        ) {
            VegIndicator(isVegetarian = isVegetarian)

            Spacer(Modifier.height(4.dp))

            Text(
                text = name,
                style = MaterialTheme.typography.titleSmall,
                color = if (isAvailable) MaterialTheme.colorScheme.onSurface
                else MaterialTheme.colorScheme.outline
            )

            if (isBestseller) {
                Spacer(Modifier.height(2.dp))
                Text(
                    text = "★ Bestseller",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.secondary
                )
            }

            Spacer(Modifier.height(4.dp))

            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 2
            )

            Spacer(Modifier.height(spacing.sm))

            Text(
                text = if (isAvailable) price else "Currently unavailable",
                style = MaterialTheme.typography.titleSmall,
                color = if (isAvailable) MaterialTheme.colorScheme.onSurface
                else MaterialTheme.colorScheme.error
            )
        }

        // Image + floating add button
        Box(
            modifier = Modifier.width(100.dp)
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = name,
                modifier = Modifier
                    .size(100.dp)
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop,
                alpha = if (isAvailable) 1f else 0.4f
            )

            if (isAvailable) {
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(end = 8.dp)
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.surface)
                        .clickable(onClick = onAddClick),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Add to cart",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FoodCardPreview() {
    EZORDERTheme {
        Column {
            FoodCard(
                name = "Chicken Biryani",
                description = "Aromatic basmati rice with tender chicken and spices",
                price = "₹220",
                imageUrl = "https://images.unsplash.com/photo-1563379091339-03246963d96c",
                isVegetarian = false,
                isAvailable = true,
                isBestseller = true,
                onAddClick = {},
                onClick = {},
                modifier = Modifier.padding(16.dp)
            )
            FoodCard(
                name = "Paneer Butter Masala",
                description = "Rich and creamy tomato-based curry with paneer",
                price = "₹190",
                imageUrl = "https://images.unsplash.com/photo-1631452180519-c014fe946bc7",
                isVegetarian = true,
                isAvailable = false,
                onAddClick = {},
                onClick = {},
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}