package com.udarnyrezhim.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = OliveDark,
    onPrimary = Cream,
    secondary = Sage,
    onSecondary = OliveDark,
    background = Cream,
    onBackground = OliveDark,
    surface = Paper,
    onSurface = OliveDark,
    error = Error
)

private val DarkColorScheme = darkColorScheme(
    primary = OliveMedium,
    onPrimary = DarkTextPrimary,
    secondary = Sage,
    onSecondary = DarkTextPrimary,
    background = DarkBackground,
    onBackground = DarkTextPrimary,
    surface = DarkSurface,
    onSurface = DarkTextPrimary,
    error = Error
)

@Composable
fun UdarnyRezhimTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = AppTypography,
        content = content
    )
}
