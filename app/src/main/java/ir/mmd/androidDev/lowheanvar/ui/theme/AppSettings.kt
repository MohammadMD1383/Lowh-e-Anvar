package ir.mmd.androidDev.lowheanvar.ui.theme

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import org.json.JSONObject

data object AppSettings {
	var headerBorder by mutableStateOf(Color(0xFFFFFF00))
	var headerText by mutableStateOf(Color(0xFFFFFFFF))
	
	fun save(context: Context) {
		context.filesDir.resolve("AppSettings.json").writeText(
			JSONObject().apply {
				put("headerBorder", headerBorder.value.toLong())
				put("headerText", headerText.value.toLong())
			}.toString(2)
		)
	}
	
	fun load(context: Context) {
		context.filesDir.resolve("AppSettings.json").takeIf { it.exists() }?.run {
			JSONObject(readText()).also {
				headerBorder = Color(it.getLong("headerBorder").toULong())
				headerText = Color(it.getLong("headerText").toULong())
			}
		}
	}
}
