package ir.mmd.androidDev.lowheanvar.ui.pages

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.DeleteOutline
import androidx.compose.material.icons.rounded.Description
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.FolderOpen
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ir.mmd.androidDev.lowheanvar.ContentManager
import ir.mmd.androidDev.lowheanvar.R
import ir.mmd.androidDev.lowheanvar.navigateSingleTop
import ir.mmd.androidDev.lowheanvar.ui.components.AppHeaderVector
import ir.mmd.androidDev.lowheanvar.ui.components.FolderComponent
import ir.mmd.androidDev.lowheanvar.ui.components.IconButton
import ir.mmd.androidDev.lowheanvar.ui.components.NoteComponent
import ir.mmd.androidDev.lowheanvar.ui.components.dialog.ConfirmDialog
import ir.mmd.androidDev.lowheanvar.ui.components.dialog.ConfirmDialog.ConfirmResult
import ir.mmd.androidDev.lowheanvar.ui.components.dialog.NewFolderDialog
import ir.mmd.androidDev.lowheanvar.ui.components.dialog.NewFolderDialog.NewFolderResult
import ir.mmd.androidDev.lowheanvar.ui.components.dialog.RenameItemDialog
import ir.mmd.androidDev.lowheanvar.ui.controllers.rememberSelectionController
import ir.mmd.androidDev.lowheanvar.ui.theme.LowheAnvarTheme
import kotlinx.coroutines.launch
import java.io.File

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomePage(navController: NavHostController) {
	val selectionController = rememberSelectionController()
	val scope = rememberCoroutineScope()
	var popupShown by remember { mutableStateOf(false) }
	var scale by remember { mutableStateOf(false) }
	val scaleFactor by animateFloatAsState(if (scale) 1f else 0f, finishedListener = { if (it == 0f) popupShown = false })
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
				title = {
					if (selectionController.selectMode) {
						val folderText = if (selectionController.hasFolderSelection()) "${selectionController.foldersCount()} ${stringResource(R.string.txt_folders)}" else ""
						val noteText = if (selectionController.hasNoteSelection()) "${selectionController.notesCount()} ${stringResource(R.string.txt_notes)}" else ""
						val separator = if (folderText.isNotEmpty() && noteText.isNotEmpty()) "${stringResource(R.string.comma)} " else ""
						Text("$folderText$separator$noteText")
					} else {
						AppHeaderVector()
					}
				},
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
	) { contentPadding ->
		Column(
			modifier = Modifier.padding(contentPadding)
		) {
			ContentManager.BreadCrumb()
			
			LazyVerticalGrid(
				columns = GridCells.Adaptive(160.dp),
				verticalArrangement = Arrangement.spacedBy(8.dp),
				horizontalArrangement = Arrangement.spacedBy(8.dp),
				contentPadding = PaddingValues(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 90.dp),
			) {
				items(ContentManager.folders, contentType = { "folder" }) { folder ->
					FolderComponent(
						selectionController = selectionController,
						folder = folder
					)
				}
				
				items(
					items = ContentManager.notes,
					span = { GridItemSpan(2) },
					contentType = { "note" }
				) { note ->
					NoteComponent(
						navController = navController,
						selectionController = selectionController,
						note = note
					)
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
