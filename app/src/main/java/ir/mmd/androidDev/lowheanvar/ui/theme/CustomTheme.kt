package ir.mmd.androidDev.lowheanvar.ui.theme

import android.content.Context
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import ir.mmd.androidDev.lowheanvar.JSONObject
import ir.mmd.androidDev.lowheanvar.getBoolean
import ir.mmd.androidDev.lowheanvar.getColor
import ir.mmd.androidDev.lowheanvar.putColor
import ir.mmd.androidDev.lowheanvar.saveTo

annotation class ThemeVariable

data object CustomTheme {
	private const val FILE_NAME = "CustomTheme.json"
	
	var useCustomTheme by mutableStateOf(false)
	
	@ThemeVariable
	var headerBorder by mutableStateOf(Color.Yellow)
	
	@ThemeVariable
	var headerText by mutableStateOf(Color.White)
	
	@ThemeVariable
	var primary by mutableStateOf(LightColorScheme.primary)
	
	@ThemeVariable
	var onPrimary by mutableStateOf(LightColorScheme.onPrimary)
	
	@ThemeVariable
	var primaryContainer by mutableStateOf(LightColorScheme.primaryContainer)
	
	@ThemeVariable
	var onPrimaryContainer by mutableStateOf(LightColorScheme.onPrimaryContainer)
	
	@ThemeVariable
	var inversePrimary by mutableStateOf(LightColorScheme.inversePrimary)
	
	@ThemeVariable
	var secondary by mutableStateOf(LightColorScheme.secondary)
	
	@ThemeVariable
	var onSecondary by mutableStateOf(LightColorScheme.onSecondary)
	
	@ThemeVariable
	var secondaryContainer by mutableStateOf(LightColorScheme.secondaryContainer)
	
	@ThemeVariable
	var onSecondaryContainer by mutableStateOf(LightColorScheme.onSecondaryContainer)
	
	@ThemeVariable
	var tertiary by mutableStateOf(LightColorScheme.tertiary)
	
	@ThemeVariable
	var onTertiary by mutableStateOf(LightColorScheme.onTertiary)
	
	@ThemeVariable
	var tertiaryContainer by mutableStateOf(LightColorScheme.tertiaryContainer)
	
	@ThemeVariable
	var onTertiaryContainer by mutableStateOf(LightColorScheme.onTertiaryContainer)
	
	@ThemeVariable
	var error by mutableStateOf(LightColorScheme.error)
	
	@ThemeVariable
	var onError by mutableStateOf(LightColorScheme.onError)
	
	@ThemeVariable
	var errorContainer by mutableStateOf(LightColorScheme.errorContainer)
	
	@ThemeVariable
	var onErrorContainer by mutableStateOf(LightColorScheme.onErrorContainer)
	
	@ThemeVariable
	var background by mutableStateOf(LightColorScheme.background)
	
	@ThemeVariable
	var onBackground by mutableStateOf(LightColorScheme.onBackground)
	
	@ThemeVariable
	var surface by mutableStateOf(LightColorScheme.surface)
	
	@ThemeVariable
	var onSurface by mutableStateOf(LightColorScheme.onSurface)
	
	@ThemeVariable
	var surfaceVariant by mutableStateOf(LightColorScheme.surfaceVariant)
	
	@ThemeVariable
	var onSurfaceVariant by mutableStateOf(LightColorScheme.onSurfaceVariant)
	
	@ThemeVariable
	var outline by mutableStateOf(LightColorScheme.outline)
	
	@ThemeVariable
	var inverseOnSurface by mutableStateOf(LightColorScheme.inverseOnSurface)
	
	@ThemeVariable
	var inverseSurface by mutableStateOf(LightColorScheme.inverseSurface)
	
	@ThemeVariable
	var surfaceTint by mutableStateOf(LightColorScheme.surfaceTint)
	
	@ThemeVariable
	var outlineVariant by mutableStateOf(LightColorScheme.outlineVariant)
	
	@ThemeVariable
	var scrim by mutableStateOf(LightColorScheme.scrim)
	
	@ThemeVariable
	var surfaceBright by mutableStateOf(LightColorScheme.surfaceBright)
	
	@ThemeVariable
	var surfaceDim by mutableStateOf(LightColorScheme.surfaceDim)
	
	@ThemeVariable
	var surfaceContainer by mutableStateOf(LightColorScheme.surfaceContainer)
	
	@ThemeVariable
	var surfaceContainerLowest by mutableStateOf(LightColorScheme.surfaceContainerLowest)
	
	@ThemeVariable
	var surfaceContainerLow by mutableStateOf(LightColorScheme.surfaceContainerLow)
	
	@ThemeVariable
	var surfaceContainerHigh by mutableStateOf(LightColorScheme.surfaceContainerHigh)
	
	@ThemeVariable
	var surfaceContainerHighest by mutableStateOf(LightColorScheme.surfaceContainerHighest)
	
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
	
	fun resetToLightColorScheme() {
		primary = LightColorScheme.primary
		onPrimary = LightColorScheme.onPrimary
		primaryContainer = LightColorScheme.primaryContainer
		onPrimaryContainer = LightColorScheme.onPrimaryContainer
		inversePrimary = LightColorScheme.inversePrimary
		secondary = LightColorScheme.secondary
		onSecondary = LightColorScheme.onSecondary
		secondaryContainer = LightColorScheme.secondaryContainer
		onSecondaryContainer = LightColorScheme.onSecondaryContainer
		tertiary = LightColorScheme.tertiary
		onTertiary = LightColorScheme.onTertiary
		tertiaryContainer = LightColorScheme.tertiaryContainer
		onTertiaryContainer = LightColorScheme.onTertiaryContainer
		error = LightColorScheme.error
		onError = LightColorScheme.onError
		errorContainer = LightColorScheme.errorContainer
		onErrorContainer = LightColorScheme.onErrorContainer
		background = LightColorScheme.background
		onBackground = LightColorScheme.onBackground
		surface = LightColorScheme.surface
		onSurface = LightColorScheme.onSurface
		surfaceVariant = LightColorScheme.surfaceVariant
		onSurfaceVariant = LightColorScheme.onSurfaceVariant
		outline = LightColorScheme.outline
		inverseOnSurface = LightColorScheme.inverseOnSurface
		inverseSurface = LightColorScheme.inverseSurface
		surfaceTint = LightColorScheme.surfaceTint
		outlineVariant = LightColorScheme.outlineVariant
		scrim = LightColorScheme.scrim
		surfaceBright = LightColorScheme.surfaceBright
		surfaceDim = LightColorScheme.surfaceDim
		surfaceContainer = LightColorScheme.surfaceContainer
		surfaceContainerLowest = LightColorScheme.surfaceContainerLowest
		surfaceContainerLow = LightColorScheme.surfaceContainerLow
		surfaceContainerHigh = LightColorScheme.surfaceContainerHigh
		surfaceContainerHighest = LightColorScheme.surfaceContainerHighest
	}
	
	fun resetToDarkColorScheme() {
		primary = DarkColorScheme.primary
		onPrimary = DarkColorScheme.onPrimary
		primaryContainer = DarkColorScheme.primaryContainer
		onPrimaryContainer = DarkColorScheme.onPrimaryContainer
		inversePrimary = DarkColorScheme.inversePrimary
		secondary = DarkColorScheme.secondary
		onSecondary = DarkColorScheme.onSecondary
		secondaryContainer = DarkColorScheme.secondaryContainer
		onSecondaryContainer = DarkColorScheme.onSecondaryContainer
		tertiary = DarkColorScheme.tertiary
		onTertiary = DarkColorScheme.onTertiary
		tertiaryContainer = DarkColorScheme.tertiaryContainer
		onTertiaryContainer = DarkColorScheme.onTertiaryContainer
		error = DarkColorScheme.error
		onError = DarkColorScheme.onError
		errorContainer = DarkColorScheme.errorContainer
		onErrorContainer = DarkColorScheme.onErrorContainer
		background = DarkColorScheme.background
		onBackground = DarkColorScheme.onBackground
		surface = DarkColorScheme.surface
		onSurface = DarkColorScheme.onSurface
		surfaceVariant = DarkColorScheme.surfaceVariant
		onSurfaceVariant = DarkColorScheme.onSurfaceVariant
		outline = DarkColorScheme.outline
		inverseOnSurface = DarkColorScheme.inverseOnSurface
		inverseSurface = DarkColorScheme.inverseSurface
		surfaceTint = DarkColorScheme.surfaceTint
		outlineVariant = DarkColorScheme.outlineVariant
		scrim = DarkColorScheme.scrim
		surfaceBright = DarkColorScheme.surfaceBright
		surfaceDim = DarkColorScheme.surfaceDim
		surfaceContainer = DarkColorScheme.surfaceContainer
		surfaceContainerLowest = DarkColorScheme.surfaceContainerLowest
		surfaceContainerLow = DarkColorScheme.surfaceContainerLow
		surfaceContainerHigh = DarkColorScheme.surfaceContainerHigh
		surfaceContainerHighest = DarkColorScheme.surfaceContainerHighest
	}
	
	fun save(context: Context) {
		JSONObject {
			put("useCustomTheme", useCustomTheme)
			putColor("headerBorder", headerBorder)
			putColor("headerText", headerText)
			putColor("primary", primary)
			putColor("onPrimary", onPrimary)
			putColor("primaryContainer", primaryContainer)
			putColor("onPrimaryContainer", onPrimaryContainer)
			putColor("inversePrimary", inversePrimary)
			putColor("secondary", secondary)
			putColor("onSecondary", onSecondary)
			putColor("secondaryContainer", secondaryContainer)
			putColor("onSecondaryContainer", onSecondaryContainer)
			putColor("tertiary", tertiary)
			putColor("onTertiary", onTertiary)
			putColor("tertiaryContainer", tertiaryContainer)
			putColor("onTertiaryContainer", onTertiaryContainer)
			putColor("error", error)
			putColor("onError", onError)
			putColor("errorContainer", errorContainer)
			putColor("onErrorContainer", onErrorContainer)
			putColor("background", background)
			putColor("onBackground", onBackground)
			putColor("surface", surface)
			putColor("onSurface", onSurface)
			putColor("surfaceVariant", surfaceVariant)
			putColor("onSurfaceVariant", onSurfaceVariant)
			putColor("outline", outline)
			putColor("inverseOnSurface", inverseOnSurface)
			putColor("inverseSurface", inverseSurface)
			putColor("surfaceTint", surfaceTint)
			putColor("outlineVariant", outlineVariant)
			putColor("scrim", scrim)
			putColor("surfaceBright", surfaceBright)
			putColor("surfaceDim", surfaceDim)
			putColor("surfaceContainer", surfaceContainer)
			putColor("surfaceContainerLowest", surfaceContainerLowest)
			putColor("surfaceContainerLow", surfaceContainerLow)
			putColor("surfaceContainerHigh", surfaceContainerHigh)
			putColor("surfaceContainerHighest", surfaceContainerHighest)
		}.saveTo(context.filesDir.resolve(FILE_NAME))
	}
	
	fun load(context: Context) {
		JSONObject(context.filesDir.resolve(FILE_NAME)) {
			getBoolean("useCustomTheme") { useCustomTheme = it }
			getColor("headerBorder") { headerBorder = it }
			getColor("headerText") { headerText = it }
			getColor("primary") { primary = it }
			getColor("onPrimary") { onPrimary = it }
			getColor("primaryContainer") { primaryContainer = it }
			getColor("onPrimaryContainer") { onPrimaryContainer = it }
			getColor("inversePrimary") { inversePrimary = it }
			getColor("secondary") { secondary = it }
			getColor("onSecondary") { onSecondary = it }
			getColor("secondaryContainer") { secondaryContainer = it }
			getColor("onSecondaryContainer") { onSecondaryContainer = it }
			getColor("tertiary") { tertiary = it }
			getColor("onTertiary") { onTertiary = it }
			getColor("tertiaryContainer") { tertiaryContainer = it }
			getColor("onTertiaryContainer") { onTertiaryContainer = it }
			getColor("error") { error = it }
			getColor("onError") { onError = it }
			getColor("errorContainer") { errorContainer = it }
			getColor("onErrorContainer") { onErrorContainer = it }
			getColor("background") { background = it }
			getColor("onBackground") { onBackground = it }
			getColor("surface") { surface = it }
			getColor("onSurface") { onSurface = it }
			getColor("surfaceVariant") { surfaceVariant = it }
			getColor("onSurfaceVariant") { onSurfaceVariant = it }
			getColor("outline") { outline = it }
			getColor("inverseOnSurface") { inverseOnSurface = it }
			getColor("inverseSurface") { inverseSurface = it }
			getColor("surfaceTint") { surfaceTint = it }
			getColor("outlineVariant") { outlineVariant = it }
			getColor("scrim") { scrim = it }
			getColor("surfaceBright") { surfaceBright = it }
			getColor("surfaceDim") { surfaceDim = it }
			getColor("surfaceContainer") { surfaceContainer = it }
			getColor("surfaceContainerLowest") { surfaceContainerLowest = it }
			getColor("surfaceContainerLow") { surfaceContainerLow = it }
			getColor("surfaceContainerHigh") { surfaceContainerHigh = it }
			getColor("surfaceContainerHighest") { surfaceContainerHighest = it }
		}
	}
}
