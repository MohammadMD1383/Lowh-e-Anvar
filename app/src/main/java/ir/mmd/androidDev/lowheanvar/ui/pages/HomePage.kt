package ir.mmd.androidDev.lowheanvar.ui.pages

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.DeleteOutline
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ir.mmd.androidDev.lowheanvar.ContentManager
import ir.mmd.androidDev.lowheanvar.R
import ir.mmd.androidDev.lowheanvar.navigateSingleTop
import ir.mmd.androidDev.lowheanvar.ui.components.AppFloatingActionButton
import ir.mmd.androidDev.lowheanvar.ui.components.AppHeader
import ir.mmd.androidDev.lowheanvar.ui.components.FolderComponent
import ir.mmd.androidDev.lowheanvar.ui.components.IconButton
import ir.mmd.androidDev.lowheanvar.ui.components.NoteComponent
import ir.mmd.androidDev.lowheanvar.ui.components.dialog.ConfirmDialog
import ir.mmd.androidDev.lowheanvar.ui.components.dialog.ConfirmDialog.ConfirmResult
import ir.mmd.androidDev.lowheanvar.ui.components.dialog.NewFolderDialog
import ir.mmd.androidDev.lowheanvar.ui.components.dialog.RenameItemDialog
import ir.mmd.androidDev.lowheanvar.ui.controllers.rememberSelectionController
import ir.mmd.androidDev.lowheanvar.ui.theme.LowheAnvarTheme
import kotlinx.coroutines.launch
import sh.calvin.reorderable.ReorderableItem
import sh.calvin.reorderable.rememberReorderableLazyGridState
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(navController: NavHostController) {
	val lazyGridState = rememberLazyGridState()
	val reorderableLazyGridState = rememberReorderableLazyGridState(lazyGridState) { from, to ->
		if (from.contentType == "folder" && to.contentType == "folder") {
			ContentManager.folders.add(to.index, ContentManager.folders.removeAt(from.index))
		}
		
		if (from.contentType == "note" && to.contentType == "note") {
			val foldersCount = ContentManager.folders.size
			ContentManager.notes.add(
				to.index - foldersCount,
				ContentManager.notes.removeAt(from.index - foldersCount)
			)
		}
	}
	val selectionController = rememberSelectionController()
	val scope = rememberCoroutineScope()
	val newFolderDialog = remember { NewFolderDialog() }
	val confirmDialog = remember { ConfirmDialog() }
	val renameDialog = remember { RenameItemDialog() }
	val context = LocalContext.current
	
	BackHandler(ContentManager.canPopStack || selectionController.selectMode) {
		if (selectionController.selectMode) {
			selectionController.clearSelection()
		} else {
			ContentManager.popStack()
		}
	}
	
	Scaffold(
		topBar = {
			TopAppBar(
				title = { AppHeader(selectionController) },
				navigationIcon = {
					if (!selectionController.selectMode) IconButton(Icons.Rounded.Settings) {
						navController.navigateSingleTop("settings")
					} else IconButton(Icons.Rounded.Close) {
						selectionController.clearSelection()
					}
				},
				actions = {
					if (!selectionController.selectMode) {
						IconButton(Icons.Rounded.Search) { }
					} else {
						if (selectionController.hasSingleSelection()) {
							IconButton(Icons.Rounded.Edit) {
								scope.launch {
									val isFolder = selectionController.hasFolderSelection()
									val path = selectionController.singleSelectionKey()
									val name = path.substringAfterLast(File.separator)
									
									val result = renameDialog.show {
										previousName = name
										this.itemType = if (isFolder) RenameItemDialog.ItemType.Folder else RenameItemDialog.ItemType.Note
									}
									
									if (result is RenameItemDialog.RenameResult.OK) {
										selectionController.clearSelection()
										if (isFolder)
											ContentManager.renameFolder(path, result.name)
										else
											ContentManager.renameNote(path, result.name)
									}
								}
							}
						}
						
						IconButton(Icons.Rounded.DeleteOutline) {
							scope.launch {
								val result = confirmDialog.show {
									title = context.getString(R.string.txt_confirm_delete)
									text = context.getString(R.string.txt_sure_to_delete)
								}
								
								if (result is ConfirmResult.OK) {
									selectionController.clearSelection()
									ContentManager.batchDelete(
										selectionController.folderSelectionKeys(),
										selectionController.noteSelectionKeys()
									)
								}
							}
						}
					}
				}
			)
		},
		floatingActionButton = {
			AppFloatingActionButton(
				selectionController = selectionController,
				navController = navController,
				newFolderDialog = newFolderDialog
			)
		}
	) { contentPadding ->
		Column(
			modifier = Modifier
				.padding(contentPadding)
				.fillMaxSize()
		) {
			ContentManager.BreadCrumb()
			
			LazyVerticalGrid(
				columns = GridCells.Adaptive(160.dp),
				verticalArrangement = Arrangement.spacedBy(8.dp),
				horizontalArrangement = Arrangement.spacedBy(8.dp),
				contentPadding = PaddingValues(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 90.dp),
				state = lazyGridState
			) {
				items(
					items = ContentManager.folders,
					contentType = { "folder" },
					key = { it.key }
				) { folder ->
					ReorderableItem(reorderableLazyGridState, folder.key) {
						FolderComponent(
							selectionController = selectionController,
							folder = folder
						)
					}
				}
				
				items(
					items = ContentManager.notes,
					span = { GridItemSpan(2) },
					contentType = { "note" },
					key = { it.key }
				) { note ->
					ReorderableItem(reorderableLazyGridState, note.key) {
						NoteComponent(
							navController = navController,
							selectionController = selectionController,
							note = note
						)
					}
				}
			}
		}
	}
	
	newFolderDialog()
	confirmDialog()
	renameDialog()
}

@Preview
@Composable
private fun LightPreview() {
	LowheAnvarTheme(darkTheme = false) {
		HomePage(rememberNavController())
	}
}

@Preview
@Composable
private fun DarkPreview() {
	LowheAnvarTheme(darkTheme = true) {
		HomePage(rememberNavController())
	}
}
