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
	var headerBorder by mutableStateOf(Color(0xFFFFFF00))
	
	@ThemeVariable
	var headerText by mutableStateOf(Color(0xFFFFFFFF))
	
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
