package ir.mmd.androidDev.lowheanvar.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ir.mmd.androidDev.lowheanvar.R
import ir.mmd.androidDev.lowheanvar.ui.components.ColorPickerDialog
import ir.mmd.androidDev.lowheanvar.ui.components.ColorPickerDialog.ColorResult
import ir.mmd.androidDev.lowheanvar.ui.theme.CustomTheme
import ir.mmd.androidDev.lowheanvar.ui.theme.LowheAnvarTheme
import ir.mmd.androidDev.lowheanvar.ui.theme.ThemeVariable
import kotlinx.coroutines.launch
import kotlin.reflect.KMutableProperty1
import kotlin.reflect.full.memberProperties

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemePage(navController: NavHostController) {
	val scope = rememberCoroutineScope()
	val colorPickerDialog = ColorPickerDialog()
	val context = LocalContext.current
	
	Scaffold(
		topBar = {
			CenterAlignedTopAppBar(
				title = { Text(stringResource(R.string.txt_theme)) },
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
		LazyColumn(modifier = Modifier.padding(contentPadding)) {
			items(CustomTheme::class.memberProperties.filter { it.annotations.any { a -> a is ThemeVariable } }) {
				val color = it.get(CustomTheme) as Color
				Row(
					verticalAlignment = Alignment.CenterVertically,
					modifier = Modifier
						.clickable {
							scope.launch {
								val result = colorPickerDialog.show(color)
								if (result is ColorResult.OK) {
									(it as KMutableProperty1<CustomTheme, Color>).set(CustomTheme, result.color)
								}
							}
						}
						.padding(16.dp)
				) {
					Text(it.name, modifier = Modifier.weight(1f))
					Box(
						modifier = Modifier
							.size(40.dp)
							.clip(RoundedCornerShape(12.dp))
							.background(color),
						content = {}
					)
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
