package ir.mmd.androidDev.lowheanvar.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DragHandle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ir.mmd.androidDev.lowheanvar.ContentManager
import ir.mmd.androidDev.lowheanvar.Folder
import ir.mmd.androidDev.lowheanvar.ui.controllers.SelectionController
import ir.mmd.androidDev.lowheanvar.ui.theme.Typography
import ir.mmd.androidDev.lowheanvar.ui.theme.dynamicCardColors
import sh.calvin.reorderable.ReorderableCollectionItemScope

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ReorderableCollectionItemScope.FolderComponent(
	selectionController: SelectionController,
	folder: Folder
) {
	val state = selectionController.stateFor(folder)
	
	Card(
		colors = dynamicCardColors(state.selected),
		modifier = Modifier
			.aspectRatio(1f)
			.clip(CardDefaults.shape)
			.combinedClickable(
				onClick = {
					if (selectionController.selectMode) {
						state.toggle()
					} else {
						ContentManager.navigateTo(folder)
					}
				},
				onLongClick = {
					selectionController.activateSelectMode()
					state.toggle()
				}
			)
	) {
		Box(
			modifier = Modifier
				.padding(8.dp)
				.fillMaxSize(),
			contentAlignment = Alignment.Center
		) {
			if (selectionController.selectMode) Icon(
				imageVector = Icons.Rounded.DragHandle,
				contentDescription = null,
				modifier = Modifier
					.align(Alignment.TopStart)
					.draggableHandle()
			)
			
			Text(
				text = folder.name,
				style = Typography.headlineMedium,
				textAlign = TextAlign.Center,
			)
		}
	}
}
