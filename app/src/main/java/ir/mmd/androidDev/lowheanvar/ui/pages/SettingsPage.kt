package ir.mmd.androidDev.lowheanvar.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ir.mmd.androidDev.lowheanvar.R
import ir.mmd.androidDev.lowheanvar.navigateSingleTop
import ir.mmd.androidDev.lowheanvar.ui.components.dialog.ColorPickerDialog
import ir.mmd.androidDev.lowheanvar.ui.components.dialog.ColorPickerDialog.ColorResult
import ir.mmd.androidDev.lowheanvar.ui.theme.AppSettings
import ir.mmd.androidDev.lowheanvar.ui.theme.CustomTheme
import ir.mmd.androidDev.lowheanvar.ui.theme.LowheAnvarTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsPage(navController: NavHostController) {
	val context = LocalContext.current
	val colorPickerDialog = ColorPickerDialog()
	val scope = rememberCoroutineScope()
	
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
		Column(
			modifier = Modifier.padding(contentPadding)
		) {
			Row(
				verticalAlignment = Alignment.CenterVertically,
				modifier = Modifier
					.clickable { navController.navigateSingleTop("theme") }
					.padding(horizontal = 16.dp, vertical = 12.dp)
			) {
				Text(stringResource(R.string.txt_custom_theme), modifier = Modifier.weight(1f))
				VerticalDivider(
					thickness = 2.dp,
					modifier = Modifier
						.padding(horizontal = 16.dp)
						.height(32.dp)
				)
				Switch(
					checked = CustomTheme.useCustomTheme,
					onCheckedChange = {
						CustomTheme.useCustomTheme = it
						CustomTheme.save(context)
					}
				)
			}
			
			Row(
				verticalAlignment = Alignment.CenterVertically,
				modifier = Modifier
					.clickable {
						scope.launch {
							val result = colorPickerDialog.show { color = AppSettings.headerBorder }
							if (result is ColorResult.OK) {
								AppSettings.headerBorder = result.color
								AppSettings.save(context)
							}
						}
					}
					.padding(16.dp)
			) {
				Text(stringResource(R.string.txt_header_border), modifier = Modifier.weight(1f))
				Box(
					modifier = Modifier
						.size(40.dp)
						.clip(RoundedCornerShape(12.dp))
						.background(AppSettings.headerBorder),
					content = {}
				)
			}
			
			Row(
				verticalAlignment = Alignment.CenterVertically,
				modifier = Modifier
					.clickable {
						scope.launch {
							val result = colorPickerDialog.show { color = AppSettings.headerText }
							if (result is ColorResult.OK) {
								AppSettings.headerText = result.color
								AppSettings.save(context)
							}
						}
					}
					.padding(16.dp)
			) {
				Text(stringResource(R.string.txt_header_text), modifier = Modifier.weight(1f))
				Box(
					modifier = Modifier
						.size(40.dp)
						.clip(RoundedCornerShape(12.dp))
						.background(AppSettings.headerText),
					content = {}
				)
			}
			
			Row(
				verticalAlignment = Alignment.CenterVertically,
				modifier = Modifier.padding(16.dp)
			) {
				Text("Editor font size", modifier = Modifier.weight(1f))
				IconButton(onClick = {
					AppSettings.editorFontSize--
					AppSettings.save(context)
				}) {
					Icon(Icons.Rounded.Remove, null)
				}
				Text(AppSettings.editorFontSize.toString())
				IconButton(onClick = {
					AppSettings.editorFontSize++
					AppSettings.save(context)
				}) {
					Icon(Icons.Rounded.Add, null)
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
