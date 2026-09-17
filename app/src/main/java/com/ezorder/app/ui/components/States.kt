package com.ezorder.app.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ezorder.app.ui.theme.EZORDERTheme
import com.ezorder.app.ui.theme.LocalEZSpacing

/**
 * Shown while data is being fetched.
 * Drop this directly into a screen body — it fills available space and centers itself.
 */
@Composable
fun LoadingState(
    modifier: Modifier = Modifier,
    message: String? = null
) {
    val spacing = LocalEZSpacing.current

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.primary,
                strokeWidth = 3.dp,
                modifier = Modifier.size(40.dp)
            )
            if (message != null) {
                Text(
                    text = message,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = spacing.md)
                )
            }
        }
    }
}

/**
 * Shown when a request succeeded but there is simply nothing to display.
 * The action button is optional — pass actionText + onAction only when
 * there's something meaningful for the user to do.
 */
@Composable
fun EmptyState(
    title: String,
    message: String,
    modifier: Modifier = Modifier,
    icon: ImageVector = Icons.Filled.Info,
    actionText: String? = null,
    onAction: (() -> Unit)? = null
) {
    val spacing = LocalEZSpacing.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(spacing.xl),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Surface(
            shape = MaterialTheme.shapes.extraLarge,
            color = MaterialTheme.colorScheme.surfaceVariant
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier
                    .padding(spacing.lg)
                    .size(40.dp)
            )
        }

        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = spacing.lg)
        )

        Text(
            text = message,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = spacing.sm)
        )

        if (actionText != null && onAction != null) {
            Box(modifier = Modifier.padding(top = spacing.lg)) {
                PrimaryButton(
                    text = actionText,
                    onClick = onAction
                )
            }
        }
    }
}

/**
 * Shown when something actually failed — no network, repository threw, etc.
 * Always gives the user a way out via onRetry.
 */
@Composable
fun ErrorState(
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
    title: String = "Something went wrong",
    message: String = "We couldn't load this right now. Please check your connection and try again.",
    retryText: String = "Try again"
) {
    val spacing = LocalEZSpacing.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(spacing.xl),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Surface(
            shape = MaterialTheme.shapes.extraLarge,
            color = MaterialTheme.colorScheme.errorContainer
        ) {
            Icon(
                imageVector = Icons.Filled.Warning,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.error,
                modifier = Modifier
                    .padding(spacing.lg)
                    .size(40.dp)
            )
        }

        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = spacing.lg)
        )

        Text(
            text = message,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = spacing.sm)
        )

        Box(modifier = Modifier.padding(top = spacing.lg)) {
            SecondaryButton(
                text = retryText,
                onClick = onRetry
            )
        }
    }
}

@Preview(showBackground = true, heightDp = 400)
@Composable
private fun LoadingStatePreview() {
    EZORDERTheme {
        LoadingState(message = "Finding restaurants near you…")
    }
}

@Preview(showBackground = true, heightDp = 500)
@Composable
private fun EmptyStatePreview() {
    EZORDERTheme {
        EmptyState(
            title = "No restaurants found",
            message = "Try a different cuisine or widen your search area.",
            actionText = "Clear filters",
            onAction = {}
        )
    }
}

@Preview(showBackground = true, heightDp = 500)
@Composable
private fun ErrorStatePreview() {
    EZORDERTheme {
        ErrorState(onRetry = {})
    }
}