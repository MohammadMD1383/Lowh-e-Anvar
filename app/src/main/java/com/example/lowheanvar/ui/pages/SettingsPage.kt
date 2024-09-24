package com.example.lowheanvar.ui.pages

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.lowheanvar.ui.theme.LowheAnvarTheme

@Composable
fun SettingsPage(navController: NavHostController) {
	Scaffold {
		Text("Settings Page", modifier = Modifier.padding(it))
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
