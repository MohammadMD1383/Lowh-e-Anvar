package com.example.lowheanvar.ui.components

import android.content.Context
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.FormatAlignLeft
import androidx.compose.material.icons.automirrored.rounded.FormatAlignRight
import androidx.compose.material.icons.automirrored.rounded.FormatIndentDecrease
import androidx.compose.material.icons.automirrored.rounded.FormatIndentIncrease
import androidx.compose.material.icons.rounded.FormatAlignCenter
import androidx.compose.material.icons.rounded.FormatBold
import androidx.compose.material.icons.rounded.FormatItalic
import androidx.compose.material.icons.rounded.FormatQuote
import androidx.compose.material.icons.rounded.FormatUnderlined
import androidx.compose.material.icons.rounded.HorizontalRule
import androidx.compose.material.icons.rounded.Looks3
import androidx.compose.material.icons.rounded.Looks4
import androidx.compose.material.icons.rounded.Looks5
import androidx.compose.material.icons.rounded.Looks6
import androidx.compose.material.icons.rounded.LooksOne
import androidx.compose.material.icons.rounded.LooksTwo
import androidx.compose.material.icons.rounded.Subscript
import androidx.compose.material.icons.rounded.Superscript
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.lowheanvar.Notifier
import com.example.lowheanvar.ui.theme.Typography
import jp.wasabeef.richeditor.RichEditor

@Composable
fun RichEditor(
	text: String,
	onTextChange: (String) -> Unit,
	modifier: Modifier = Modifier
) {
	val backgroundColor = MaterialTheme.colorScheme.background.toArgb()
	val fontColor = OutlinedTextFieldDefaults.colors().unfocusedTextColor.toArgb()
	val fontSize = Typography.bodyLarge.fontSize.value.toInt()
	val scrollState = rememberScrollState()
	
	/* --------------------------------------------------- */
	val bold = remember { Notifier() }
	val italic = remember { Notifier() }
	val underline = remember { Notifier() }
	val alignLeft = remember { Notifier() }
	val alignCenter = remember { Notifier() }
	val alignRight = remember { Notifier() }
	val subscript = remember { Notifier() }
	val superscript = remember { Notifier() }
	val blockquote = remember { Notifier() }
	val heading1 = remember { Notifier() }
	val heading2 = remember { Notifier() }
	val heading3 = remember { Notifier() }
	val heading4 = remember { Notifier() }
	val heading5 = remember { Notifier() }
	val heading6 = remember { Notifier() }
	val indent = remember { Notifier() }
	val outdent = remember { Notifier() }
	val hr = remember { Notifier() }
	/* --------------------------------------------------- */
	
	Column(modifier = Modifier.then(modifier)) {
		AndroidView(
			factory = { context ->
				AppRichEditor(context).apply {
					setPlaceholder("Start typing here ...")
					setEditorBackgroundColor(backgroundColor)
					setEditorFontColor(fontColor)
					setEditorFontSize(fontSize)
					setEditorDirection(AppRichEditor.Direction.Auto)
					setOnTextChangeListener(onTextChange)
					html = text
					
					bold { setBold() }
					italic { setItalic() }
					underline { setUnderline() }
					alignLeft { setAlignLeft() }
					alignCenter { setAlignCenter() }
					alignRight { setAlignRight() }
					subscript { setSubscript() }
					superscript { setSuperscript() }
					blockquote { setBlockquote() }
					heading1 { setHeading(1) }
					heading2 { setHeading(2) }
					heading3 { setHeading(3) }
					heading4 { setHeading(4) }
					heading5 { setHeading(5) }
					heading6 { setHeading(6) }
					indent { setIndent() }
					outdent { setOutdent() }
					hr { insertLineSeparator() }
				}
			},
			modifier = Modifier
				.fillMaxWidth()
				.weight(1f)
				.padding(horizontal = 16.dp)
		)
		
		Row(
			modifier = Modifier
				.imePadding()
				.horizontalScroll(scrollState)
		) {
			IconButton(onClick = { bold.notify() }) {
				Icon(Icons.Rounded.FormatBold, null)
			}
			
			IconButton(onClick = { italic.notify() }) {
				Icon(Icons.Rounded.FormatItalic, null)
			}
			
			IconButton(onClick = { underline.notify() }) {
				Icon(Icons.Rounded.FormatUnderlined, null)
			}
			
			IconButton(onClick = { alignLeft.notify() }) {
				Icon(Icons.AutoMirrored.Rounded.FormatAlignLeft, null)
			}
			
			IconButton(onClick = { alignCenter.notify() }) {
				Icon(Icons.Rounded.FormatAlignCenter, null)
			}
			
			IconButton(onClick = { alignRight.notify() }) {
				Icon(Icons.AutoMirrored.Rounded.FormatAlignRight, null)
			}
			
			IconButton(onClick = { hr.notify() }) {
				Icon(Icons.Rounded.HorizontalRule, null)
			}
			
			IconButton(onClick = { subscript.notify() }) {
				Icon(Icons.Rounded.Subscript, null)
			}
			
			IconButton(onClick = { superscript.notify() }) {
				Icon(Icons.Rounded.Superscript, null)
			}
			
			IconButton(onClick = { blockquote.notify() }) {
				Icon(Icons.Rounded.FormatQuote, null)
			}
			
			IconButton(onClick = { heading1.notify() }) {
				Icon(Icons.Rounded.LooksOne, null)
			}
			
			IconButton(onClick = { heading2.notify() }) {
				Icon(Icons.Rounded.LooksTwo, null)
			}
			
			IconButton(onClick = { heading3.notify() }) {
				Icon(Icons.Rounded.Looks3, null)
			}
			
			IconButton(onClick = { heading4.notify() }) {
				Icon(Icons.Rounded.Looks4, null)
			}
			
			IconButton(onClick = { heading5.notify() }) {
				Icon(Icons.Rounded.Looks5, null)
			}
			
			IconButton(onClick = { heading6.notify() }) {
				Icon(Icons.Rounded.Looks6, null)
			}
			
			IconButton(onClick = { indent.notify() }) {
				Icon(Icons.AutoMirrored.Rounded.FormatIndentIncrease, null)
			}
			
			IconButton(onClick = { outdent.notify() }) {
				Icon(Icons.AutoMirrored.Rounded.FormatIndentDecrease, null)
			}
		}
	}
}

private class AppRichEditor(context: Context) : RichEditor(context) {
	init {
		exec(
			"""javascript:
			window.onresize = () => {
				document.documentElement.style.height = window.innerHeight + "px";
			}
			window.onresize();
			
			RE.editor.style.backgroundImage="";
		""".trimIndent()
		)
	}
	
	enum class Direction(val representation: String) {
		Auto("auto"),
		Ltr("ltr"),
		Rtl("rtl")
	}
	
	fun setEditorDirection(dir: Direction) {
		exec("javascript:RE.editor.setAttribute('dir', '${dir.representation}')")
	}
	
	fun insertLineSeparator() {
		exec("javascript:document.execCommand('insertHorizontalRule', false, null)")
	}
}
