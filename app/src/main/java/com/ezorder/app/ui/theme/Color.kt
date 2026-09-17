package com.ezorder.app.ui.theme

import androidx.compose.ui.graphics.Color

// ===== Brand Colors =====
// Warm terracotta - primary brand color, used for CTAs like "Reserve Table"
val EZOrangePrimary = Color(0xFFE85D26)
val EZOrangePrimaryDark = Color(0xFFC44A1A)
val EZOrangePrimaryLight = Color(0xFFFF8A5C)

// Deep warm red-brown - secondary accent, used sparingly (e.g. spicy indicators)
val EZAccentRed = Color(0xFFB33F30)

// Golden amber - used for ratings, highlights, "bestseller" badges
val EZAmber = Color(0xFFF2A93B)

// ===== Neutrals =====
val EZCharcoal = Color(0xFF1F1B18)        // primary text
val EZCharcoalSoft = Color(0xFF4A4340)    // secondary text
val EZGrayMedium = Color(0xFF8A817C)      // tertiary text, hints
val EZGrayLight = Color(0xFFE8E2DE)       // dividers, borders
val EZOffWhite = Color(0xFFFBF8F6)        // app background (warm white, not stark)
val EZSurface = Color(0xFFFFFFFF)         // card surfaces

// ===== Semantic Colors =====
val EZSuccess = Color(0xFF3E8E5A)         // order confirmed, open status
val EZError = Color(0xFFD64545)           // errors, closed status, rejected orders
val EZWarning = Color(0xFFE8A93B)         // pending states

// ===== Dark Theme Variants =====
val EZDarkBackground = Color(0xFF161311)
val EZDarkSurface = Color(0xFF241F1C)
val EZDarkCharcoal = Color(0xFFF2ECE8)    // text on dark bg