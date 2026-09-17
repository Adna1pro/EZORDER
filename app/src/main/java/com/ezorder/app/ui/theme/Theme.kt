package com.ezorder.app.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val EZLightColorScheme = lightColorScheme(
    primary = EZOrangePrimary,
    onPrimary = EZSurface,
    primaryContainer = EZOrangePrimaryLight,
    onPrimaryContainer = EZCharcoal,

    secondary = EZAmber,
    onSecondary = EZCharcoal,

    error = EZError,
    onError = EZSurface,

    background = EZOffWhite,
    onBackground = EZCharcoal,

    surface = EZSurface,
    onSurface = EZCharcoal,
    surfaceVariant = EZGrayLight,
    onSurfaceVariant = EZCharcoalSoft,

    outline = EZGrayMedium,
)

private val EZDarkColorScheme = darkColorScheme(
    primary = EZOrangePrimaryLight,
    onPrimary = EZDarkBackground,
    primaryContainer = EZOrangePrimaryDark,
    onPrimaryContainer = EZDarkCharcoal,

    secondary = EZAmber,
    onSecondary = EZDarkBackground,

    error = EZError,
    onError = EZDarkBackground,

    background = EZDarkBackground,
    onBackground = EZDarkCharcoal,

    surface = EZDarkSurface,
    onSurface = EZDarkCharcoal,
    surfaceVariant = EZCharcoalSoft,
    onSurfaceVariant = EZGrayLight,

    outline = EZGrayMedium,
)

@Composable
fun EZORDERTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color (Material You) is turned OFF by default.
    // EZORDER has a deliberate warm brand palette — we don't want
    // it overridden by the user's wallpaper colors on Android 12+.
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> EZDarkColorScheme
        else -> EZLightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    CompositionLocalProvider(LocalEZSpacing provides EZSpacing()) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = EZORDERTypography,
            shapes = EZORDERShapes,
            content = content
        )
    }
}