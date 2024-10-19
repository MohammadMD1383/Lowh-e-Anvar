package ir.mmd.androidDev.lowheanvar.ui.theme

import android.content.Context
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import org.json.JSONObject

annotation class ThemeVariable

data object CustomTheme {
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
		context.filesDir.resolve("CustomTheme.json").writeText(
			JSONObject().apply {
				put("useCustomTheme", useCustomTheme)
				put("headerBorder", headerBorder.value.toLong())
				put("headerText", headerText.value.toLong())
				put("primary", primary.value.toLong())
				put("onPrimary", onPrimary.value.toLong())
				put("primaryContainer", primaryContainer.value.toLong())
				put("onPrimaryContainer", onPrimaryContainer.value.toLong())
				put("inversePrimary", inversePrimary.value.toLong())
				put("secondary", secondary.value.toLong())
				put("onSecondary", onSecondary.value.toLong())
				put("secondaryContainer", secondaryContainer.value.toLong())
				put("onSecondaryContainer", onSecondaryContainer.value.toLong())
				put("tertiary", tertiary.value.toLong())
				put("onTertiary", onTertiary.value.toLong())
				put("tertiaryContainer", tertiaryContainer.value.toLong())
				put("onTertiaryContainer", onTertiaryContainer.value.toLong())
				put("error", error.value.toLong())
				put("onError", onError.value.toLong())
				put("errorContainer", errorContainer.value.toLong())
				put("onErrorContainer", onErrorContainer.value.toLong())
				put("background", background.value.toLong())
				put("onBackground", onBackground.value.toLong())
				put("surface", surface.value.toLong())
				put("onSurface", onSurface.value.toLong())
				put("surfaceVariant", surfaceVariant.value.toLong())
				put("onSurfaceVariant", onSurfaceVariant.value.toLong())
				put("outline", outline.value.toLong())
				put("inverseOnSurface", inverseOnSurface.value.toLong())
				put("inverseSurface", inverseSurface.value.toLong())
				put("surfaceTint", surfaceTint.value.toLong())
				put("outlineVariant", outlineVariant.value.toLong())
				put("scrim", scrim.value.toLong())
				put("surfaceBright", surfaceBright.value.toLong())
				put("surfaceDim", surfaceDim.value.toLong())
				put("surfaceContainer", surfaceContainer.value.toLong())
				put("surfaceContainerLowest", surfaceContainerLowest.value.toLong())
				put("surfaceContainerLow", surfaceContainerLow.value.toLong())
				put("surfaceContainerHigh", surfaceContainerHigh.value.toLong())
				put("surfaceContainerHighest", surfaceContainerHighest.value.toLong())
			}.toString(2)
		)
	}
	
	fun load(context: Context) {
		context.filesDir.resolve("CustomTheme.json").takeIf { it.exists() }?.run {
			JSONObject(readText()).also {
				useCustomTheme = it.getBoolean("useCustomTheme")
				headerBorder = Color(it.getLong("headerBorder").toULong())
				headerText = Color(it.getLong("headerText").toULong())
				primary = Color(it.getLong("primary").toULong())
				onPrimary = Color(it.getLong("onPrimary").toULong())
				primaryContainer = Color(it.getLong("primaryContainer").toULong())
				onPrimaryContainer = Color(it.getLong("onPrimaryContainer").toULong())
				inversePrimary = Color(it.getLong("inversePrimary").toULong())
				secondary = Color(it.getLong("secondary").toULong())
				onSecondary = Color(it.getLong("onSecondary").toULong())
				secondaryContainer = Color(it.getLong("secondaryContainer").toULong())
				onSecondaryContainer = Color(it.getLong("onSecondaryContainer").toULong())
				tertiary = Color(it.getLong("tertiary").toULong())
				onTertiary = Color(it.getLong("onTertiary").toULong())
				tertiaryContainer = Color(it.getLong("tertiaryContainer").toULong())
				onTertiaryContainer = Color(it.getLong("onTertiaryContainer").toULong())
				error = Color(it.getLong("error").toULong())
				onError = Color(it.getLong("onError").toULong())
				errorContainer = Color(it.getLong("errorContainer").toULong())
				onErrorContainer = Color(it.getLong("onErrorContainer").toULong())
				background = Color(it.getLong("background").toULong())
				onBackground = Color(it.getLong("onBackground").toULong())
				surface = Color(it.getLong("surface").toULong())
				onSurface = Color(it.getLong("onSurface").toULong())
				surfaceVariant = Color(it.getLong("surfaceVariant").toULong())
				onSurfaceVariant = Color(it.getLong("onSurfaceVariant").toULong())
				outline = Color(it.getLong("outline").toULong())
				inverseOnSurface = Color(it.getLong("inverseOnSurface").toULong())
				inverseSurface = Color(it.getLong("inverseSurface").toULong())
				surfaceTint = Color(it.getLong("surfaceTint").toULong())
				outlineVariant = Color(it.getLong("outlineVariant").toULong())
				scrim = Color(it.getLong("scrim").toULong())
				surfaceBright = Color(it.getLong("surfaceBright").toULong())
				surfaceDim = Color(it.getLong("surfaceDim").toULong())
				surfaceContainer = Color(it.getLong("surfaceContainer").toULong())
				surfaceContainerLowest = Color(it.getLong("surfaceContainerLowest").toULong())
				surfaceContainerLow = Color(it.getLong("surfaceContainerLow").toULong())
				surfaceContainerHigh = Color(it.getLong("surfaceContainerHigh").toULong())
				surfaceContainerHighest = Color(it.getLong("surfaceContainerHighest").toULong())
			}
		}
	}
}
