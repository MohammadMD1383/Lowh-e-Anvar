package ir.mmd.androidDev.lowheanvar.ui.components

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.viewinterop.AndroidView
import ir.mmd.androidDev.lowheanvar.AppSettings

@OptIn(ExperimentalStdlibApi::class)
@SuppressLint("SetJavaScriptEnabled")
@Composable
fun HtmlViewer(
	html: String,
	modifier: Modifier = Modifier
) {
	val backgroundColor = MaterialTheme.colorScheme.background.toArgb()
	val foregroundColor = MaterialTheme.colorScheme.onBackground.toArgb()
	
	AndroidView(
		factory = { context ->
			(object : WebView(context) {
				init {
					setWebContentsDebuggingEnabled(true)
				}
			}).apply {
				webViewClient = object : WebViewClient() {
					override fun onPageFinished(view: WebView?, url: String?) {
						evaluateJavascript(
							"""
							document.body.style.color='#${foregroundColor.toHexString().drop(2)}';
							document.documentElement.style.fontSize='${AppSettings.editorFontSize}px';
							""".trimMargin(), null
						)
					}
				}
				
				setBackgroundColor(backgroundColor)
				settings.javaScriptEnabled = true
			}
		},
		update = {
			it.loadData(html, "text/html", "UTF-8")
		},
		modifier = Modifier.then(modifier)
	)
}
