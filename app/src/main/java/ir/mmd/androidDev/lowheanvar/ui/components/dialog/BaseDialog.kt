package ir.mmd.androidDev.lowheanvar.ui.components.dialog

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

abstract class BaseDialog<D : BaseDialog<D, T, R>, T, R>(private val properties: DialogProperties = DialogProperties()) {
	private var shown by mutableStateOf(false)
	private val channel = Channel<T>()
	
	@Composable
	abstract fun Content(scope: CoroutineScope)
	
	abstract fun createResult(result: T): R
	abstract suspend fun onDismissRequest()
	
	protected suspend fun done(result: T) {
		channel.send(result)
	}
	
	open fun before() {}
	open fun after() {}
	
	suspend fun show(setup: (D.() -> Unit)? = null): R {
		if (shown) {
			throw IllegalStateException("Dialog is already shown")
		}
		
		before()
		setup?.invoke(this as D)
		shown = true
		val result = channel.receive()
		shown = false
		after()
		return createResult(result)
	}
	
	@Composable
	operator fun invoke() {
		val scope = rememberCoroutineScope()
		
		if (shown) Dialog(
			properties = properties,
			onDismissRequest = {
				scope.launch { onDismissRequest() }
			}
		) { Content(scope) }
	}
}
