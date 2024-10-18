package ir.mmd.androidDev.lowheanvar.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

annotation class ThemeVariable

data object CustomTheme {
	var useCustomTheme by mutableStateOf(false)
	
	@ThemeVariable
	var primary by mutableStateOf(Color(0xFF6750A4))
	
	@ThemeVariable
	var onPrimary by mutableStateOf(Color(0xFFFFFFFF))
	
	@ThemeVariable
	var primaryContainer by mutableStateOf(Color(0xFFEADDFF))
	
	@ThemeVariable
	var onPrimaryContainer by mutableStateOf(Color(0xFF21005D))
	
	@ThemeVariable
	var inversePrimary by mutableStateOf(Color(0xFFD0BCFF))
	
	@ThemeVariable
	var secondary by mutableStateOf(Color(0xFF625B71))
	
	@ThemeVariable
	var onSecondary by mutableStateOf(Color(0xFFFFFFFF))
	
	@ThemeVariable
	var secondaryContainer by mutableStateOf(Color(0xFFE8DEF8))
	
	@ThemeVariable
	var onSecondaryContainer by mutableStateOf(Color(0xFF1D192B))
	
	@ThemeVariable
	var tertiary by mutableStateOf(Color(0xFF7D5260))
	
	@ThemeVariable
	var onTertiary by mutableStateOf(Color(0xFFFFFFFF))
	
	@ThemeVariable
	var tertiaryContainer by mutableStateOf(Color(0xFFFFD8E4))
	
	@ThemeVariable
	var onTertiaryContainer by mutableStateOf(Color(0xFF31111D))
	
	@ThemeVariable
	var error by mutableStateOf(Color(0xFFB3261E))
	
	@ThemeVariable
	var onError by mutableStateOf(Color(0xFFFFFFFF))
	
	@ThemeVariable
	var errorContainer by mutableStateOf(Color(0xFFF9DEDC))
	
	@ThemeVariable
	var onErrorContainer by mutableStateOf(Color(0xFF410E0B))
	
	@ThemeVariable
	var background by mutableStateOf(Color(0xFFFFFBFE))
	
	@ThemeVariable
	var onBackground by mutableStateOf(Color(0xFF1C1B1F))
	
	@ThemeVariable
	var surface by mutableStateOf(Color(0xFFFFFBFE))
	
	@ThemeVariable
	var onSurface by mutableStateOf(Color(0xFF1C1B1F))
	
	@ThemeVariable
	var surfaceVariant by mutableStateOf(Color(0xFFE7E0EC))
	
	@ThemeVariable
	var onSurfaceVariant by mutableStateOf(Color(0xFF49454F))
	
	@ThemeVariable
	var outline by mutableStateOf(Color(0xFF79747E))
	
	@ThemeVariable
	var inverseOnSurface by mutableStateOf(Color(0xFFF4EFF4))
	
	@ThemeVariable
	var inverseSurface by mutableStateOf(Color(0xFF313033))
	
	@ThemeVariable
	var surfaceTint by mutableStateOf(Color(0xFF6750A4))
	
	@ThemeVariable
	var outlineVariant by mutableStateOf(Color(0xFFCAC4D0))
	
	@ThemeVariable
	var scrim by mutableStateOf(Color(0xFF000000))
	
	@ThemeVariable
	var surfaceBright by mutableStateOf(Color(0xFFF4EFF4))
	
	@ThemeVariable
	var surfaceDim by mutableStateOf(Color(0xFF1C1B1F))
	
	@ThemeVariable
	var surfaceContainer by mutableStateOf(Color(0xFFF1E7FA))
	
	@ThemeVariable
	var surfaceContainerLowest by mutableStateOf(Color(0xFFF1E7FA))
	
	@ThemeVariable
	var surfaceContainerLow by mutableStateOf(Color(0xFFEADDFA))
	
	@ThemeVariable
	var surfaceContainerHigh by mutableStateOf(Color(0xFFD0BCFF))
	
	@ThemeVariable
	var surfaceContainerHighest by mutableStateOf(Color(0xFFD0BCFF))
	
	@get:Composable
	val colorScheme: ColorScheme
		get() = ColorScheme(
			primary = primary,
			onPrimary = onPrimary,
			primaryContainer = primaryContainer,
			onPrimaryContainer = onPrimaryContainer,
			inversePrimary = inversePrimary,
			secondary = secondary,
			onSecondary = onSecondary,
			secondaryContainer = secondaryContainer,
			onSecondaryContainer = onSecondaryContainer,
			tertiary = tertiary,
			onTertiary = onTertiary,
			tertiaryContainer = tertiaryContainer,
			onTertiaryContainer = onTertiaryContainer,
			error = error,
			onError = onError,
			errorContainer = errorContainer,
			onErrorContainer = onErrorContainer,
			background = background,
			onBackground = onBackground,
			surface = surface,
			onSurface = onSurface,
			surfaceVariant = surfaceVariant,
			onSurfaceVariant = onSurfaceVariant,
			outline = outline,
			inverseOnSurface = inverseOnSurface,
			inverseSurface = inverseSurface,
			surfaceTint = surfaceTint,
			outlineVariant = outlineVariant,
			scrim = scrim,
			surfaceBright = surfaceBright,
			surfaceDim = surfaceDim,
			surfaceContainer = surfaceContainer,
			surfaceContainerLowest = surfaceContainerLowest,
			surfaceContainerLow = surfaceContainerLow,
			surfaceContainerHigh = surfaceContainerHigh,
			surfaceContainerHighest = surfaceContainerHighest,
		)
}
