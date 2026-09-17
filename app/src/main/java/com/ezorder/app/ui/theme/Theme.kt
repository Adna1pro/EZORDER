package com.ezorder.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = EzOrderPrimary,
    onPrimary = EzOrderOnPrimary,
    secondary = EzOrderSecondary,
    onSecondary = EzOrderOnSecondary,
    background = EzOrderBackground,
    onBackground = EzOrderOnBackground,
    surface = EzOrderSurface,
    onSurface = EzOrderOnSurface,
    error = EzOrderError,
    onError = EzOrderOnError,
)

// Dark scheme is provided so the app doesn't break on dark-mode devices,
// but per spec section 55 "dark mode if desired" is Phase 11 polish —
// this is not tuned/reviewed yet, just a non-crashing default.
private val DarkColors = darkColorScheme(
    primary = EzOrderPrimary,
    onPrimary = EzOrderOnPrimary,
    secondary = EzOrderSecondary,
    onSecondary = EzOrderOnSecondary,
)

/**
 * EZORDER's root Compose theme. Wrap all screen content in this.
 *
 * This is the Phase 0 skeleton only: a working MaterialTheme with brand
 * colors and a type scale. EZORDERSpacing and EZORDERShapes tokens
 * (spec section 57) are added in Phase 1 once real screens exist to
 * validate spacing/corner-radius choices against.
 */
@Composable
fun EZOrderTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colorScheme,
        typography = EzOrderTypography,
        content = content
    )
}
