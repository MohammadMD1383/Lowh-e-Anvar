package ir.mmd.androidDev.lowheanvar.ui.pages

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ir.mmd.androidDev.lowheanvar.R
import ir.mmd.androidDev.lowheanvar.ui.components.ColorPreferenceSettingsItem
import ir.mmd.androidDev.lowheanvar.ui.components.dialog.ColorPickerDialog
import ir.mmd.androidDev.lowheanvar.ui.theme.CustomTheme
import ir.mmd.androidDev.lowheanvar.ui.theme.LowheAnvarTheme
import ir.mmd.androidDev.lowheanvar.ui.theme.ThemeVariable
import kotlin.reflect.KMutableProperty1
import kotlin.reflect.full.declaredMemberProperties

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemePage(navController: NavHostController) {
	val colorPickerDialog = ColorPickerDialog()
	val context = LocalContext.current
	
	Scaffold(
		topBar = {
			CenterAlignedTopAppBar(
				title = { Text(stringResource(R.string.txt_custom_theme)) },
				navigationIcon = {
					IconButton(onClick = { navController.popBackStack() }) {
						Icon(Icons.AutoMirrored.Rounded.ArrowBack, null)
					}
				},
				actions = {
					IconButton(onClick = {
						CustomTheme.save(context)
						navController.popBackStack()
					}) {
						Icon(Icons.Rounded.Check, null)
					}
				}
			)
		}
	) { contentPadding ->
		CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
			LazyColumn(modifier = Modifier.padding(contentPadding)) {
				items(
					CustomTheme::class.declaredMemberProperties.filter {
						it.annotations.any { a -> a is ThemeVariable }
					}.map {
						it as KMutableProperty1<CustomTheme, Color>
					},
					contentType = { "color-preference" }
				) { property ->
					val currentColor = property.get(CustomTheme)
					ColorPreferenceSettingsItem(
						colorPickerDialog = colorPickerDialog,
						color = currentColor,
						title = property.name,
						onResult = {
							property.set(CustomTheme, it)
						}
					)
				}
				
				item(contentType = "text-button") {
					TextButton(
						onClick = { CustomTheme.resetToLightColorScheme() },
						modifier = Modifier
							.fillMaxWidth()
							.padding(horizontal = 16.dp)
					) {
						Text(stringResource(R.string.txt_reset_light_scheme))
					}
				}
				
				item(contentType = "text-button") {
					TextButton(
						onClick = { CustomTheme.resetToDarkColorScheme() },
						modifier = Modifier
							.fillMaxWidth()
							.padding(horizontal = 16.dp)
					) {
						Text(stringResource(R.string.txt_reset_dark_scheme))
					}
				}
			}
		}
		
		colorPickerDialog()
	}
}

@Preview
@Composable
private fun LightPreview() {
	LowheAnvarTheme(darkTheme = false) {
		ThemePage(rememberNavController())
	}
}

@Preview
@Composable
private fun DarkPreview() {
	LowheAnvarTheme(darkTheme = true) {
		ThemePage(rememberNavController())
	}
}
