package ir.mmd.androidDev.lowheanvar.ui.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.ArrowForwardIos
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ir.mmd.androidDev.lowheanvar.R
import ir.mmd.androidDev.lowheanvar.navigateSingleTop
import ir.mmd.androidDev.lowheanvar.ui.components.SettingsItem
import ir.mmd.androidDev.lowheanvar.ui.theme.LowheAnvarTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsPage(navController: NavHostController) {
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
			SettingsItem("Theme", Icons.AutoMirrored.Rounded.ArrowForwardIos) {
				navController.navigateSingleTop("theme")
			}
		}
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
