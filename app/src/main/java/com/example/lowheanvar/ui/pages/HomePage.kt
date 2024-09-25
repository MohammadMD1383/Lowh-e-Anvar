package com.example.lowheanvar.ui.pages

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateFloatAsState
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
import androidx.compose.material.icons.rounded.Description
import androidx.compose.material.icons.rounded.FolderOpen
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.lowheanvar.ContentManager
import com.example.lowheanvar.navigateSingleTop
import com.example.lowheanvar.plus
import com.example.lowheanvar.ui.components.NewFolderDialog
import com.example.lowheanvar.ui.components.NewFolderDialog.DialogResult
import com.example.lowheanvar.ui.theme.LowheAnvarTheme
import com.example.lowheanvar.ui.theme.Typography
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun HomePage(navController: NavHostController) {
	val scope = rememberCoroutineScope()
	var searchTerm by remember { mutableStateOf("") }
	var popupShown by remember { mutableStateOf(false) }
	var scale by remember { mutableStateOf(false) }
	val scaleFactor by animateFloatAsState(if (scale) 1f else 0f, finishedListener = { if (it == 0f) popupShown = false })
	val newFolderDialog = remember { NewFolderDialog() }
	
	BackHandler(ContentManager.canPopStack) {
		ContentManager.popStack()
	}
	
	Scaffold(
		topBar = {
			CenterAlignedTopAppBar(
				title = { /* Text("السلام علیک یا صاحب الزمان") */ },
				navigationIcon = {
					IconButton(onClick = {
						navController.navigateSingleTop("settings")
					}) {
						Icon(Icons.Rounded.Settings, "")
					}
				}
			)
		},
		floatingActionButton = {
			FloatingActionButton(onClick = {
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
									if (result is DialogResult.Create) {
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
		LazyVerticalGrid(
			columns = GridCells.Adaptive(160.dp),
			verticalArrangement = Arrangement.spacedBy(8.dp),
			horizontalArrangement = Arrangement.spacedBy(8.dp),
			contentPadding = contentPadding + PaddingValues(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 90.dp),
		) {
			item(span = { GridItemSpan(2) }) {
				OutlinedTextField(
					value = searchTerm,
					onValueChange = { searchTerm = it },
					placeholder = {
						Text(
							text = "Search ...",
							modifier = Modifier.fillMaxWidth(),
							textAlign = TextAlign.Center
						)
					},
					modifier = Modifier.fillMaxWidth()
				)
			}
			
			items(ContentManager.folders) {
				Card(
					modifier = Modifier.aspectRatio(1f),
					onClick = {
						ContentManager.navigateTo(it)
					},
				) {
					Box(
						modifier = Modifier
							.padding(8.dp)
							.fillMaxSize(),
						contentAlignment = Alignment.Center
					) {
						Text(
							text = it.name,
							style = Typography.headlineMedium,
							textAlign = TextAlign.Center,
						)
					}
				}
			}
			
			items(
				items = ContentManager.notes,
				span = { GridItemSpan(2) }
			) {
				Card(
					onClick = {
						ContentManager.openNote = it
						navController.navigateSingleTop("view")
					},
				) {
					Column(
						modifier = Modifier
							.padding(16.dp)
							.fillMaxWidth(),
					) {
						Text(
							text = it.title,
							style = Typography.headlineSmall,
						)
						
						Spacer(Modifier.height(8.dp))
						
						Text(
							text = it.content.replace("<[^>]*>".toRegex(), "").take(100),
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
	
	newFolderDialog()
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
