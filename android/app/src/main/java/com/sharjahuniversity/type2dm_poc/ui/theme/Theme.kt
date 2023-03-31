package com.sharjahuniversity.type2dm_poc.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = CustomBluePrimary,
    primaryVariant = CustomBlueDark,
    secondary = CustomBlueLight
)

private val LightColorPalette = lightColors(
    primary = CustomBluePrimary,
    primaryVariant = CustomBlueDark,
    secondary = CustomBlueLight,
    background = BackgroundColor

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun Type2DMPocTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = LightColorPalette,
        typography = Typography2,
        shapes = Shapes,
        content = content
    )
}