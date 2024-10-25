package ir.mmd.androidDev.lowheanvar.ui.controllers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import ir.mmd.androidDev.lowheanvar.Folder
import ir.mmd.androidDev.lowheanvar.Note

class SelectionController {
	inner class State(
		private val list: SnapshotStateList<String>,
		private val key: String
	) {
		private var index by mutableIntStateOf(list.indexOf(key))
		val selected by derivedStateOf { index != -1 && list.isNotEmpty() }
		
		fun toggle() {
			if (selected) {
				list.removeAt(index)
				index = -1
			} else {
				list.add(key)
				index = list.size - 1
			}
			
			if (selectedFolders.isEmpty() and selectedNotes.isEmpty()) {
				selectMode = false
			}
		}
	}
	
	var selectMode by mutableStateOf(false)
		private set
	
	private val selectedFolders = mutableStateListOf<String>()
	private val selectedNotes = mutableStateListOf<String>()
	
	fun hasSingleSelection() = (selectedFolders.size + selectedNotes.size) == 1
	fun hasFolderSelection() = selectedFolders.isNotEmpty()
	fun hasNoteSelection() = selectedNotes.isNotEmpty()
	fun singleSelectionKey() = selectedFolders.firstOrNull() ?: selectedNotes.firstOrNull()!!
	fun folderSelectionKeys() = selectedFolders.toList()
	fun noteSelectionKeys() = selectedNotes.toList()
	fun foldersCount() = selectedFolders.size
	fun notesCount() = selectedNotes.size
	
	@Composable
	fun stateFor(folder: Folder): State {
		return State(selectedFolders, folder.key)
	}
	
	@Composable
	fun stateFor(note: Note): State {
		return State(selectedNotes, note.key)
	}
	
	fun activateSelectMode() {
		selectMode = true
	}
	
	fun clearSelection() {
		selectMode = false
		selectedFolders.clear()
		selectedNotes.clear()
	}
}

@Composable
fun rememberSelectionController() = remember { SelectionController() }
