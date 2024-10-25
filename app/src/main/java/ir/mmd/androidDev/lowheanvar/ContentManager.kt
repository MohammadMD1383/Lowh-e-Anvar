package ir.mmd.androidDev.lowheanvar

import android.content.Context
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
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
val orderRegex = """^(([0-9]+?\.)|([a-z]+?\.))""".toRegex()

class ContentManagerClass(context: Context) {
	private val contentRoot = context.filesDir.resolve("content-root")
	private var currentDir = contentRoot
	private var currentPath by mutableStateOf("")
	var folders = mutableStateListOf<Folder>()
	var notes = mutableStateListOf<Note>()
	var foldersOrderChanged = false
	var notesOrderChanged = false
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
		val directories = currentPath.split(File.separator).toMutableList().apply { removeAt(0) }
		val last = directories.removeLastOrNull()
		val lazyListState = rememberLazyListState()
		
		LaunchedEffect(currentPath) {
			lazyListState.animateScrollToItem(lazyListState.layoutInfo.totalItemsCount - 1)
		}
		
		LazyRow(
			verticalAlignment = Alignment.CenterVertically,
			modifier = Modifier.fillMaxWidth(),
			state = lazyListState
		) {
			item(contentType = "home") {
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
			}
			
			if (last != null) {
				item(contentType = "arrow") {
					arrow()
				}
			}
			
			var dirPath = contentRoot.path
			directories.forEach {
				dirPath += "${File.separator}$it"
				val capture = dirPath
				
				item(contentType = "text-button") {
					TextButton(
						colors = ButtonDefaults.textButtonColors(contentColor = LocalContentColor.current),
						onClick = { navigateTo(File(capture)) },
					) {
						Text(it.replace(orderRegex, ""))
					}
				}
				
				item(contentType = "arrow") {
					arrow()
				}
			}
			
			if (last != null) {
				item(contentType = "text-button") {
					TextButton(
						colors = ButtonDefaults.textButtonColors(disabledContentColor = ButtonDefaults.textButtonColors().contentColor),
						onClick = {},
						enabled = false
					) {
						Text(last.replace(orderRegex, ""))
					}
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
		
		folders.sortBy { it.order.value }
		notes.sortBy { it.order.value }
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
		val newFile = openNote!!.file.resolveSibling(openNote!!.order.toString() + newTitle)
		val newNote = Note(newFile)
		openNote!!.file.renameTo(newFile)
		newNote.content = newContent
		notes[notes.indexOf(openNote!!)] = newNote
		openNote = newNote
	}
	
	fun renameNote(path: String, newName: String) {
		val index = notes.indexOfFirst { it.file.path == path }
		val target = notes[index]
		val file = target.file
		val newFile = file.resolveSibling(target.order.toString() + newName)
		file.renameTo(newFile)
		
		notes[index] = Note(newFile)
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
	
	fun renameFolder(path: String, newName: String) {
		val index = folders.indexOfFirst { it.file.path == path }
		val target = folders[index]
		val file = target.file
		val newFile = file.resolveSibling(target.order.toString() + newName)
		file.renameTo(newFile)
		
		folders[index] = Folder(newFile)
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
	
	fun batchDelete(folderPaths: List<String>, notePaths: List<String>) {
		folders.filter { it.file.path in folderPaths }.forEach {
			deleteFolder(it)
		}
		notes.filter { it.file.path in notePaths }.forEach {
			deleteNote(it)
		}
	}
	
	private fun mergeOrders(o1: Order, o2: Order): Order {
		return when (o1) {
			is Order.Unordered -> o2
			is Order.Numeric -> if (o2 is Order.Alphabetic) throw RuntimeException("This should never happen") else o1
			is Order.Alphabetic -> if (o2 is Order.Numeric) throw RuntimeException("This should never happen") else o1
		}
	}
	
	private fun changeOrder(order: Order) = when (order) {
		is Order.Unordered,
		is Order.Alphabetic -> Order.Numeric()
		
		is Order.Numeric -> Order.Alphabetic()
	}
	
	private fun saveFoldersOrder() {
		val order = folders.fold<Folder, Order>(Order.Unordered) { order, folder ->
			mergeOrders(order, folder.order)
		}
		var newOrder = changeOrder(order)
		
		val tmp = folders.map {
			it.move(newOrder).also {
				newOrder = newOrder.next()
			}
		}
		folders.clear()
		folders.addAll(tmp)
	}
	
	private fun saveNotesOrder() {
		val order = notes.fold<Note, Order>(Order.Unordered) { order, note ->
			mergeOrders(order, note.order)
		}
		var newOrder = changeOrder(order)
		
		val tmp = notes.map {
			it.move(newOrder).also {
				newOrder = newOrder.next()
			}
		}
		notes.clear()
		notes.addAll(tmp)
	}
	
	fun saveOrders() {
		if (foldersOrderChanged) {
			foldersOrderChanged = false
			saveFoldersOrder()
		}
		if (notesOrderChanged) {
			notesOrderChanged = false
			saveNotesOrder()
		}
	}
}

sealed interface Order {
	data class Alphabetic(val order: Base26 = Base26(0)) : Order {
		override val value = order.toInt()
		override fun next(): Alphabetic {
			return Alphabetic(order.next())
		}
		
		override fun toString() = "$order."
	}
	
	data class Numeric(val order: Int = 0) : Order {
		override val value = order
		override fun next(): Order {
			return Numeric(this.order + 1)
		}
		
		override fun toString() = "$order."
	}
	
	data object Unordered : Order {
		override val value = 0
		override fun next(): Order = throw UnsupportedOperationException("This should never happen")
	}
	
	val value: Int
	fun next(): Order
}

class Folder(val file: File) {
	var order: Order = Order.Unordered
	val key: String = file.path
	val name: String = file.name.replace(orderRegex) {
		when {
			it.groups[2] != null -> order = Order.Numeric(it.value.dropLast(1).toInt())
			it.groups[3] != null -> order = Order.Alphabetic(it.value.dropLast(1).toBase26())
		}
		""
	}
	
	fun move(newOrder: Order): Folder {
		val newFile = file.resolveSibling(file.path.dropLastWhile { it != '/' } + newOrder + name)
		file.renameTo(newFile)
		return Folder(newFile)
	}
}

class Note(val file: File) {
	var order: Order = Order.Unordered
	val key: String = file.path
	val title: String = file.name.replace(orderRegex) {
		when {
			it.groups[2] != null -> order = Order.Numeric(it.value.dropLast(1).toInt())
			it.groups[3] != null -> order = Order.Alphabetic(it.value.dropLast(1).toBase26())
		}
		""
	}
	
	private var lastRead: Long = 0
	private var cachedContent: String = ""
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
	
	fun move(newOrder: Order): Note {
		val newFile = file.resolveSibling(file.path.dropLastWhile { it != '/' } + newOrder + title)
		file.renameTo(newFile)
		return Note(newFile)
	}
}
