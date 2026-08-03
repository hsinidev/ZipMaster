package com.example.zipmaster.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = ArchiveOrangePrimary,
    secondary = NeutralGreySecondary,
    background = BackgroundDarkBrown,
    surface = SurfaceDarkRust,
    onPrimary = BackgroundDarkBrown,
    onSecondary = BackgroundDarkBrown,
    onBackground = OnSurfaceWhite,
    onSurface = OnSurfaceWhite
)

@Composable
fun ZipMasterTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}
