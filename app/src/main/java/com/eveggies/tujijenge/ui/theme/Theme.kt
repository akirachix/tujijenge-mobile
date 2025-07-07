package com.eveggies.tujijenge.ui.theme
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme(
    primary = TujijengeGreen,
    onPrimary = TujijengeWhite,
    secondary = TujijengeLightGreen,
    onSecondary = TujijengeGreen,
    background = TujijengeWhite,
    onBackground = TujijengeGreen,
    surface = TujijengeWhite,
    onSurface = TujijengeGreen,
    outline = TujijengeGrey
)

private val DarkColorScheme = darkColorScheme(
    primary = TujijengeLightGreen,
    onPrimary = TujijengeGreen,
    secondary = TujijengeGreen,
    onSecondary = TujijengeWhite,
    background = TujijengeGreen,
    onBackground = TujijengeWhite,
    surface = TujijengeGreen,
    onSurface = TujijengeWhite,
    outline = TujijengeGrey
)
@Composable
fun TujijengeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    MaterialTheme(
        colorScheme = colorScheme,
        typography = TujijengeTypography,
        content = content
    )
}















