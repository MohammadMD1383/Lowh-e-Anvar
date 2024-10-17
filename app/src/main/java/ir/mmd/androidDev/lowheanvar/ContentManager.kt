package ir.mmd.androidDev.lowheanvar

import android.content.Context
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ir.mmd.androidDev.lowheanvar.ui.components.rememberReusableComponent
import java.io.File

lateinit var ContentManager: ContentManagerClass

class ContentManagerClass(context: Context) {
	private val contentRoot = context.filesDir.resolve("content-root")
	private var currentDir = contentRoot
	private var currentPath by mutableStateOf("")
	var folders = mutableStateListOf<Folder>()
	var notes = mutableStateListOf<Note>()
	var openNote by mutableStateOf<Note?>(null)
	var canPopStack by mutableStateOf(false)
	var editNote = false
	
	init {
		if (!contentRoot.exists()) {
			contentRoot.mkdirs()
		}
		
		update()
	}
	
	@Composable
	fun BreadCrumb() {
		val arrow = rememberReusableComponent { Icon(Icons.AutoMirrored.Rounded.KeyboardArrowRight, null) }
		val directories = currentPath.split(File.separator).toMutableList().apply { removeFirst() }
		val last = directories.removeLastOrNull()
		val scrollState = rememberScrollState()
		
		LaunchedEffect(scrollState.maxValue) {
			scrollState.animateScrollTo(scrollState.maxValue)
		}
		
		Row(
			verticalAlignment = Alignment.CenterVertically,
			modifier = Modifier
				.fillMaxWidth()
				.horizontalScroll(scrollState)
		) {
			IconButton(
				enabled = last != null,
				onClick = { navigateTo(contentRoot) }
			) {
				Icon(
					imageVector = Icons.Rounded.Home,
					contentDescription = null,
					tint = if (last == null) MaterialTheme.colorScheme.primary else LocalContentColor.current
				)
			}
			
			if (last != null) {
				arrow()
			}
			
			var dirPath = contentRoot.path
			directories.forEach {
				dirPath += "${File.separator}$it"
				val capture = dirPath
				
				TextButton(
					colors = ButtonDefaults.textButtonColors(contentColor = LocalContentColor.current),
					onClick = { navigateTo(File(capture)) },
				) {
					Text(it)
				}
				arrow()
			}
			
			if (last != null) {
				TextButton(
					colors = ButtonDefaults.textButtonColors(disabledContentColor = ButtonDefaults.textButtonColors().contentColor),
					onClick = {},
					enabled = false
				) {
					Text(last)
				}
			}
		}
	}
	
	private fun update() {
		currentPath = currentDir.path.drop(contentRoot.parentFile!!.path.length + 1)
		
		folders.clear()
		notes.clear()
		
		currentDir.listFiles()!!.forEach {
			if (it.isDirectory) {
				folders.add(Folder(it))
			} else {
				notes.add(Note(it))
			}
		}
	}
	
	private fun navigateTo(file: File) {
		currentDir = file
		canPopStack = currentDir.path != contentRoot.path
		update()
	}
	
	fun navigateTo(folder: Folder) {
		navigateTo(folder.file)
	}
	
	fun createFolder(name: String): Folder {
		val file = currentDir.resolve(name)
		val folder = Folder(file)
		if (file.mkdir()) {
			folders.add(folder)
		}
		
		return folder
	}
	
	fun createNote(title: String, content: String): Note {
		val newFile = currentDir.resolve(title)
		newFile.createNewFile()
		newFile.writeText(content)
		val note = Note(newFile)
		notes.add(note)
		return note
	}
	
	fun editNote(newTitle: String, newContent: String) {
		val newFile = openNote!!.file.resolveSibling(newTitle)
		val newNote = Note(newFile)
		openNote!!.file.renameTo(newFile)
		newNote.content = newContent
		
		notes.remove(openNote!!)
		notes.add(newNote)
		
		openNote = newNote
	}
	
	fun deleteNote(note: Note) {
		note.clearCache()
		note.file.delete()
		notes.remove(note)
	}
	
	fun deleteFolder(folder: Folder) {
		folder.file.deleteRecursively()
		folders.remove(folder)
	}
	
	fun popStack(): Boolean {
		if (canPopStack) {
			currentDir = currentDir.parentFile!!
			canPopStack = currentDir.path != contentRoot.path
			update()
			return true
		}
		
		return false
	}
	
	fun batchDelete(folderPaths: Set<String>, notePaths: Set<String>) {
		folders.filter { it.file.path in folderPaths }.forEach {
			deleteFolder(it)
		}
		notes.filter { it.file.path in notePaths }.forEach {
			deleteNote(it)
		}
	}
}

sealed interface Item

class Folder(val file: File) : Item {
	val name get() = file.name
}

class Note(val file: File) : Item {
	val title get() = file.name
	
	var lastRead: Long = 0
	var cachedContent: String = ""
	var content: String
		get() {
			if (file.lastModified() > lastRead) {
				cachedContent = file.readText()
				lastRead = file.lastModified()
			}
			return cachedContent
		}
		set(value) {
			file.writeText(value)
			lastRead = file.lastModified()
			cachedContent = value
		}
	
	fun clearCache() {
		cachedContent = ""
		lastRead = 0
	}
}
