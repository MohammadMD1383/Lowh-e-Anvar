package ir.mmd.androidDev.lowheanvar.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ir.mmd.androidDev.lowheanvar.ContentManager
import ir.mmd.androidDev.lowheanvar.Note
import ir.mmd.androidDev.lowheanvar.navigateSingleTop
import ir.mmd.androidDev.lowheanvar.ui.controllers.SelectionController
import ir.mmd.androidDev.lowheanvar.ui.theme.Typography
import ir.mmd.androidDev.lowheanvar.ui.theme.dynamicCardColors

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteComponent(
	navController: NavHostController,
	selectionController: SelectionController,
	note: Note
) {
	val state = selectionController.stateFor(note)
	
	Card(
		colors = dynamicCardColors(state.selected),
		modifier = Modifier
			.clip(CardDefaults.shape)
			.combinedClickable(
				onClick = {
					if (!selectionController.selectMode) {
						ContentManager.openNote = note
						navController.navigateSingleTop("view")
					} else {
						state.toggle()
					}
				},
				onLongClick = {
					selectionController.activateSelectMode()
					state.toggle()
				}
			)
	) {
		Column(
			modifier = Modifier
				.padding(16.dp)
				.fillMaxWidth(),
		) {
			Text(
				text = note.title,
				style = Typography.headlineSmall,
			)
			
			Spacer(Modifier.height(8.dp))
			
			Text(
				text = note.content.replace("<[^>]*>".toRegex(), "").take(100),
				style = Typography.bodyMedium,
				maxLines = 1,
				overflow = TextOverflow.Ellipsis,
				modifier = Modifier.fillMaxWidth(0.7f)
			)
		}
	}
}
