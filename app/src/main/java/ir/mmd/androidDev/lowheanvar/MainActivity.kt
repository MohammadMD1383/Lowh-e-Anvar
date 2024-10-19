package ir.mmd.androidDev.lowheanvar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ir.mmd.androidDev.lowheanvar.ui.pages.ContentPage
import ir.mmd.androidDev.lowheanvar.ui.pages.HomePage
import ir.mmd.androidDev.lowheanvar.ui.pages.NoteViewPage
import ir.mmd.androidDev.lowheanvar.ui.pages.SettingsPage
import ir.mmd.androidDev.lowheanvar.ui.pages.ThemePage
import ir.mmd.androidDev.lowheanvar.ui.theme.LowheAnvarTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			LowheAnvarTheme {
				MainComponent()
			}
		}
	}
}

@Composable
private fun MainComponent() {
	val navController = rememberNavController()
	
	NavHost(
		navController = navController,
		startDestination = "home",
	) {
		composable(
			route = "home",
			enterTransition = {
				when (initialState.destination.route) {
					"settings" -> slideInHorizontally { it }
					"content" -> slideInVertically { -it }
					else -> null
				}
			},
			exitTransition = {
				when (targetState.destination.route) {
					"settings" -> slideOutHorizontally { it }
					"content" -> slideOutVertically { -it }
					else -> null
				}
			}
		) { HomePage(navController) }
		
		composable(
			route = "settings",
			enterTransition = {
				when (initialState.destination.route) {
					"home" -> slideInHorizontally { -it }
					else -> null
				}
			},
			exitTransition = {
				when (targetState.destination.route) {
					"home" -> slideOutHorizontally { -it }
					else -> null
				}
			}
		) { SettingsPage(navController) }
		
		composable(
			route = "content",
			enterTransition = {
				slideInVertically { it }
			},
			exitTransition = {
				slideOutVertically { it }
			}
		) { ContentPage(navController) }
		
		composable(route = "view") { NoteViewPage(navController) }
		
		composable(route = "theme") { ThemePage(navController) }
	}
}

@Preview
@Composable
private fun LightPreview() {
	LowheAnvarTheme(darkTheme = false) {
		MainComponent()
	}
}

@Preview
@Composable
private fun DarkPreview() {
	LowheAnvarTheme(darkTheme = true) {
		MainComponent()
	}
}
