package ir.mmd.androidDev.lowheanvar.ui.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ir.mmd.androidDev.lowheanvar.ContentManager
import ir.mmd.androidDev.lowheanvar.R
import ir.mmd.androidDev.lowheanvar.navigateSingleTop
import ir.mmd.androidDev.lowheanvar.ui.components.HtmlViewer
import ir.mmd.androidDev.lowheanvar.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteViewPage(navController: NavController) {
	var dropdownShown by remember { mutableStateOf(false) }
	
	Scaffold(
		topBar = {
			CenterAlignedTopAppBar(
				title = { Text(stringResource(R.string.txt_view_note)) },
				navigationIcon = {
					IconButton(
						onClick = {
							navController.popBackStack()
						}
					) {
						Icon(Icons.AutoMirrored.Rounded.ArrowBack, null)
					}
				},
				actions = {
					IconButton(onClick = { dropdownShown = true }) {
						Icon(Icons.Rounded.MoreVert, null)
						
						DropdownMenu(
							expanded = dropdownShown,
							onDismissRequest = { dropdownShown = false }
						) {
							DropdownMenuItem(
								text = { Text(stringResource(R.string.txt_edit)) },
								onClick = {
									dropdownShown = false
									ContentManager.editNote = true
									navController.navigateSingleTop("content")
								}
							)
							
							DropdownMenuItem(
								text = { Text(stringResource(R.string.txt_delete)) },
								onClick = {
									dropdownShown = false
									ContentManager.deleteNote(ContentManager.openNote!!)
									ContentManager.openNote = null
									navController.popBackStack()
								}
							)
						}
					}
				}
			)
		}
	) {
		Column(
			modifier = Modifier
				.padding(it)
				.padding(16.dp)
		) {
			Text(
				text = ContentManager.openNote?.title ?: "",
				style = Typography.headlineMedium
			)
			
			Spacer(Modifier.height(8.dp))
			
			HtmlViewer(
				html = ContentManager.openNote?.content ?: "",
				modifier = Modifier.weight(1f)
			)
		}
	}
}
