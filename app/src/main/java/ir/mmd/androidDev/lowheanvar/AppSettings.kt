package ir.mmd.androidDev.lowheanvar

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

data object AppSettings {
	private const val FILE_NAME = "AppSettings.json"
	
	var headerBorder by mutableStateOf(Color(0xFFFFFF00))
	var headerText by mutableStateOf(Color(0xFFFFFFFF))
	var editorFontSize by mutableIntStateOf(16)
	
	fun save(context: Context) {
		JSONObject {
			putColor("headerBorder", headerBorder)
			putColor("headerText", headerText)
			put("editorFontSize", editorFontSize)
		}.saveTo(context.filesDir.resolve(FILE_NAME))
	}
	
	fun load(context: Context) {
		JSONObject(context.filesDir.resolve(FILE_NAME)) {
			getColor("headerBorder") { headerBorder = it }
			getColor("headerText") { headerText = it }
			getInt("editorFontSize") { editorFontSize = it }
		}
	}
}
