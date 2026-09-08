package com.meridian.compass.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryRed,
    secondary = SuccessGreen,
    tertiary = SunYellow,
    background = DarkBackground,
    surface = DarkSurface,
    onBackground = LightText,
    onSurface = LightText,
    onPrimary = DarkBackground,
    surfaceVariant = DarkPanel,
    onSurfaceVariant = MutedText,
    outline = BorderGray
)

@Composable
fun MeridianCompassTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = DarkColorScheme
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
