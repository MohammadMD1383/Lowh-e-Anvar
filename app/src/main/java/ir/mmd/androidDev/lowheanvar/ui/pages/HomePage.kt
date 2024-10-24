package ir.mmd.androidDev.lowheanvar.ui.pages

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
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
import ir.mmd.androidDev.lowheanvar.ui.components.dialog.ConfirmDialog
import ir.mmd.androidDev.lowheanvar.ui.components.dialog.ConfirmDialog.ConfirmResult
import ir.mmd.androidDev.lowheanvar.ui.components.dialog.NewFolderDialog
import ir.mmd.androidDev.lowheanvar.ui.components.dialog.NewFolderDialog.NewFolderResult
import ir.mmd.androidDev.lowheanvar.ui.components.dialog.RenameItemDialog
import ir.mmd.androidDev.lowheanvar.AppSettings
import ir.mmd.androidDev.lowheanvar.ui.theme.CustomTheme
import ir.mmd.androidDev.lowheanvar.ui.theme.LowheAnvarTheme
import ir.mmd.androidDev.lowheanvar.ui.theme.Typography
import kotlinx.coroutines.launch
import java.io.File

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomePage(navController: NavHostController) {
	var folderSelectCount by remember { mutableIntStateOf(0) }
	var noteSelectCount by remember { mutableIntStateOf(0) }
	var selectMode by remember { mutableStateOf(false) }
	val selectedFolders = remember { mutableStateMapOf<String, Boolean>() }
	val selectedNotes = remember { mutableStateMapOf<String, Boolean>() }
	val scope = rememberCoroutineScope()
	var popupShown by remember { mutableStateOf(false) }
	var scale by remember { mutableStateOf(false) }
	val scaleFactor by animateFloatAsState(if (scale) 1f else 0f, finishedListener = { if (it == 0f) popupShown = false })
	val newFolderDialog = remember { NewFolderDialog() }
	val confirmDialog = remember { ConfirmDialog() }
	val renameDialog = remember { RenameItemDialog() }
	val context = LocalContext.current
	
	LaunchedEffect(folderSelectCount, noteSelectCount) {
		if (folderSelectCount == 0 && noteSelectCount == 0) {
			selectMode = false
		}
	}
	
	LaunchedEffect(selectMode) {
		if (!selectMode) {
			selectedFolders.clear()
			selectedNotes.clear()
			folderSelectCount = 0
			noteSelectCount = 0
		}
	}
	
	BackHandler(ContentManager.canPopStack || selectMode) {
		if (selectMode) {
			selectMode = false
		} else {
			ContentManager.popStack()
		}
	}
	
	Scaffold(
		topBar = {
			TopAppBar(
				title = {
					if (selectMode) {
						val folderText = if (folderSelectCount > 0) "$folderSelectCount ${stringResource(R.string.txt_folders)}" else ""
						val noteText = if (noteSelectCount > 0) "$noteSelectCount ${stringResource(R.string.txt_notes)}" else ""
						val separator = if (folderText.isNotEmpty() && noteText.isNotEmpty()) "${stringResource(R.string.comma)} " else ""
						Text("$folderText$separator$noteText")
					} else {
						Box(contentAlignment = Alignment.Center) {
							Icon(
								painter = painterResource(R.drawable.lowh_e_anvar_border),
								contentDescription = null,
								tint = if (CustomTheme.useCustomTheme) CustomTheme.headerBorder else AppSettings.headerBorder
							)
							Icon(
								painter = painterResource(R.drawable.lowh_e_anvar_text),
								contentDescription = null,
								tint = if (CustomTheme.useCustomTheme) CustomTheme.headerText else AppSettings.headerText
							)
						}
					}
				},
				navigationIcon = {
					if (!selectMode) {
						IconButton(onClick = {
							navController.navigateSingleTop("settings")
						}) {
							Icon(Icons.Rounded.Settings, null)
						}
					} else {
						IconButton(onClick = { selectMode = false }) {
							Icon(Icons.Rounded.Close, null)
						}
					}
				},
				actions = {
					if (!selectMode) {
						IconButton(onClick = {}) {
							Icon(Icons.Rounded.Search, null)
						}
					} else {
						if (folderSelectCount + noteSelectCount == 1) {
							IconButton(onClick = {
								scope.launch {
									val isFolder = folderSelectCount > 0
									val path: String
									val name: String
									(if (isFolder) selectedFolders.filterValues { it }.keys.first()
									else selectedNotes.filterValues { it }.keys.first()).let {
										path = it
										name = it.substringAfterLast(File.separator)
									}
									
									val result = renameDialog.show {
										previousName = name
										this.itemType = if (isFolder) RenameItemDialog.ItemType.Folder else RenameItemDialog.ItemType.Note
									}
									
									if (result is RenameItemDialog.RenameResult.OK) {
										selectMode = false
										if (isFolder)
											ContentManager.renameFolder(path, result.name)
										else
											ContentManager.renameNote(path, result.name)
									}
								}
							}) {
								Icon(Icons.Rounded.Edit, null)
							}
						}
						
						IconButton(onClick = {
							scope.launch {
								val result = confirmDialog.show {
									title = context.getString(R.string.txt_confirm_delete)
									text = context.getString(R.string.txt_sure_to_delete)
								}
								
								if (result is ConfirmResult.OK) {
									selectMode = false
									ContentManager.batchDelete(
										selectedFolders.filterValues { it }.keys,
										selectedNotes.filterValues { it }.keys
									)
								}
							}
						}) {
							Icon(Icons.Rounded.DeleteOutline, null)
						}
					}
				}
			)
		},
		floatingActionButton = {
			if (!selectMode) FloatingActionButton(onClick = {
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
				items(ContentManager.folders, contentType = { "Folder" }) { folder ->
					Card(
						colors = CardDefaults.cardColors(containerColor = if (selectedFolders[folder.file.path] == true) MaterialTheme.colorScheme.primaryContainer else Color.Unspecified),
						modifier = Modifier
							.aspectRatio(1f)
							.clip(CardDefaults.shape)
							.combinedClickable(
								onClick = {
									if (selectMode) {
										selectedFolders[folder.file.path] = (!(selectedFolders[folder.file.path] ?: false)).also {
											if (it) folderSelectCount++ else folderSelectCount--
										}
									} else {
										ContentManager.navigateTo(folder)
									}
								},
								onLongClick = {
									if (!selectMode) {
										selectMode = true
										selectedFolders[folder.file.path] = true
										folderSelectCount++
									} else {
										selectedFolders[folder.file.path] = (!(selectedFolders[folder.file.path] ?: false)).also {
											if (it) folderSelectCount++ else folderSelectCount--
										}
									}
								}
							)
					) {
						Box(
							modifier = Modifier
								.padding(8.dp)
								.fillMaxSize(),
							contentAlignment = Alignment.Center
						) {
							Text(
								text = folder.name,
								style = Typography.headlineMedium,
								textAlign = TextAlign.Center,
							)
						}
					}
				}
				
				items(
					items = ContentManager.notes,
					span = { GridItemSpan(2) },
					contentType = { "Note" }
				) { note ->
					Card(
						colors = CardDefaults.cardColors(containerColor = if (selectedNotes[note.file.path] == true) MaterialTheme.colorScheme.primaryContainer else Color.Unspecified),
						modifier = Modifier
							.clip(CardDefaults.shape)
							.combinedClickable(
								onClick = {
									if (!selectMode) {
										ContentManager.openNote = note
										navController.navigateSingleTop("view")
									} else {
										selectedNotes[note.file.path] = (!(selectedNotes[note.file.path] ?: false)).also {
											if (it) noteSelectCount++ else noteSelectCount--
										}
									}
								},
								onLongClick = {
									if (!selectMode) {
										selectMode = true
										selectedNotes[note.file.path] = true
										noteSelectCount++
									} else {
										selectedNotes[note.file.path] = (!(selectedNotes[note.file.path] ?: false)).also {
											if (it) noteSelectCount++ else noteSelectCount--
										}
									}
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
