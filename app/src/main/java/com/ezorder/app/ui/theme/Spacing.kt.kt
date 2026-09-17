package com.ezorder.app.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class EZSpacing(
    val xs: Dp = 4.dp,      // tight gaps (icon-to-text)
    val sm: Dp = 8.dp,      // small internal padding
    val md: Dp = 16.dp,     // default screen/card padding
    val lg: Dp = 24.dp,     // section spacing
    val xl: Dp = 32.dp,     // large section breaks
    val xxl: Dp = 48.dp     // major screen-level breaks (e.g. splash)
)

// CompositionLocal lets any composable access spacing via
// EZORDERTheme.spacing.md — same pattern Compose uses for
// MaterialTheme.colorScheme and MaterialTheme.typography.
val LocalEZSpacing = staticCompositionLocalOf { EZSpacing() }