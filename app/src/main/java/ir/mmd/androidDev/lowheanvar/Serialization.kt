package ir.mmd.androidDev.lowheanvar

import androidx.compose.ui.graphics.Color
import org.json.JSONObject
import java.io.File

fun JSONObject(block: JSONObject.() -> Unit): JSONObject {
	return JSONObject().apply(block)
}

fun JSONObject.putColor(key: String, color: Color) {
	put(key, color.value.toLong())
}

fun JSONObject.getColor(key: String, block: (Color) -> Unit) {
	if (has(key)) block(Color(getLong(key).toULong()))
}

fun JSONObject.getInt(key: String, block: (Int) -> Unit) {
	if (has(key)) block(getInt(key))
}

fun JSONObject.getBoolean(key: String, block: (Boolean) -> Unit) {
	if (has(key)) block(getBoolean(key))
}

fun JSONObject.saveTo(file: File) {
	file.writeText(toString())
}

@Suppress("FunctionName")
fun JSONObject(file: File, block: JSONObject.() -> Unit) {
	if (file.exists()) block(JSONObject(file.readText()))
}
