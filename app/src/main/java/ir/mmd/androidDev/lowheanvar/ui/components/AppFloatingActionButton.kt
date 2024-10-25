package ir.mmd.androidDev.lowheanvar.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Description
import androidx.compose.material.icons.rounded.FolderOpen
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.navigation.NavHostController
import ir.mmd.androidDev.lowheanvar.ContentManager
import ir.mmd.androidDev.lowheanvar.navigateSingleTop
import ir.mmd.androidDev.lowheanvar.ui.components.dialog.NewFolderDialog
import ir.mmd.androidDev.lowheanvar.ui.components.dialog.NewFolderDialog.NewFolderResult
import ir.mmd.androidDev.lowheanvar.ui.controllers.SelectionController
import kotlinx.coroutines.launch

@Composable
fun AppFloatingActionButton(
	selectionController: SelectionController,
	navController: NavHostController,
	newFolderDialog: NewFolderDialog
) {
	val scope = rememberCoroutineScope()
	var popupShown by remember { mutableStateOf(false) }
	var scale by remember { mutableStateOf(false) }
	val scaleFactor by animateFloatAsState(if (scale) 1f else 0f, finishedListener = { if (it == 0f) popupShown = false })
	
	if (!selectionController.selectMode) FloatingActionButton(onClick = {
		if (popupShown) scale = false else popupShown = true
	}) {
		Icon(Icons.Rounded.Add, null)
		
		if (popupShown) Popup(
			alignment = Alignment.BottomCenter,
			offset = IntOffset(0, -LocalDensity.current.run { (56 + 24).dp.roundToPx() }),
			properties = PopupProperties(clippingEnabled = false, usePlatformDefaultWidth = false),
		) {
			LaunchedEffect(Unit) {
				scale = true
			}
			Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
				SmallFloatingActionButton(
					onClick = {
						scale = false
						navController.navigateSingleTop("content")
					},
					modifier = Modifier.scale(scaleFactor)
				) {
					Icon(Icons.Rounded.Description, null)
				}
				
				SmallFloatingActionButton(
					onClick = {
						scale = false
						
						scope.launch {
							val result = newFolderDialog.show()
							if (result is NewFolderResult.Create) {
								ContentManager.createFolder(result.name)
							}
						}
					},
					modifier = Modifier.scale(scaleFactor)
				) {
					Icon(Icons.Rounded.FolderOpen, null)
				}
			}
		}
	}
}
