package com.example.lowheanvar

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import java.io.File

lateinit var ContentManager: ContentManagerClass

class ContentManagerClass(private val context: Context) {
	private val contentRoot = context.filesDir.resolve("content-root")
	private var currentDir = contentRoot
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
	
	private fun update() {
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
	
	fun navigateTo(folder: Folder) {
		currentDir = folder.file
		canPopStack = currentDir.path != contentRoot.path
		update()
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
	
	fun popStack(): Boolean {
		if (canPopStack) {
			currentDir = currentDir.parentFile!!
			canPopStack = currentDir.path != contentRoot.path
			update()
			return true
		}
		
		return false
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
