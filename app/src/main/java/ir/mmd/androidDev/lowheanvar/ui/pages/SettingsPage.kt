package ir.mmd.androidDev.lowheanvar.ui.pages

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ir.mmd.androidDev.lowheanvar.R
import ir.mmd.androidDev.lowheanvar.navigateSingleTop
import ir.mmd.androidDev.lowheanvar.ui.components.ColorPreferenceSettingsItem
import ir.mmd.androidDev.lowheanvar.ui.components.NumberPreferenceSettingsItem
import ir.mmd.androidDev.lowheanvar.ui.components.SwitchSettingsItem
import ir.mmd.androidDev.lowheanvar.ui.components.dialog.ColorPickerDialog
import ir.mmd.androidDev.lowheanvar.AppSettings
import ir.mmd.androidDev.lowheanvar.ui.theme.CustomTheme
import ir.mmd.androidDev.lowheanvar.ui.theme.LowheAnvarTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsPage(navController: NavHostController) {
	val context = LocalContext.current
	val colorPickerDialog = ColorPickerDialog()
	
	Scaffold(
		topBar = {
			CenterAlignedTopAppBar(
				title = { Text(stringResource(R.string.txt_settings)) },
				navigationIcon = {
					IconButton(onClick = { navController.popBackStack() }) {
						Icon(Icons.AutoMirrored.Rounded.ArrowBack, null)
					}
				}
			)
		}
	) { contentPadding ->
		LazyColumn(
			modifier = Modifier.padding(contentPadding)
		) {
			item(contentType = "text-switch") {
				SwitchSettingsItem(
					title = stringResource(R.string.txt_custom_theme),
					checked = CustomTheme.useCustomTheme,
					onCheckedChange = {
						CustomTheme.useCustomTheme = it
						CustomTheme.save(context)
					},
					onClick = {
						navController.navigateSingleTop("theme")
					}
				)
			}
			
			item(contentType = "color-preference") {
				ColorPreferenceSettingsItem(
					colorPickerDialog = colorPickerDialog,
					title = stringResource(R.string.txt_header_border),
					color = AppSettings.headerBorder,
					onResult = {
						AppSettings.headerBorder = it
						AppSettings.save(context)
					}
				)
			}
			
			item(contentType = "color-preference") {
				ColorPreferenceSettingsItem(
					colorPickerDialog = colorPickerDialog,
					title = stringResource(R.string.txt_header_text),
					color = AppSettings.headerText,
					onResult = {
						AppSettings.headerText = it
						AppSettings.save(context)
					}
				)
			}
			
			item(contentType = "number-preference") {
				NumberPreferenceSettingsItem(
					title = stringResource(R.string.txt_editor_font_size),
					value = AppSettings.editorFontSize,
					onValueChange = {
						AppSettings.editorFontSize = it
						AppSettings.save(context)
					}
				)
			}
		}
		
		colorPickerDialog()
	}
}

@Preview
@Composable
private fun LightPreview() {
	LowheAnvarTheme(darkTheme = false) {
		SettingsPage(rememberNavController())
	}
}

@Preview
@Composable
private fun DarkPreview() {
	LowheAnvarTheme(darkTheme = true) {
		SettingsPage(rememberNavController())
	}
}
