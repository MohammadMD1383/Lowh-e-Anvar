package ir.mmd.androidDev.lowheanvar.ui.components.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ir.mmd.androidDev.lowheanvar.R
import ir.mmd.androidDev.lowheanvar.ui.theme.Typography
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ConfirmDialog : BaseDialog<ConfirmDialog, Boolean, ConfirmDialog.ConfirmResult>() {
	sealed interface ConfirmResult {
		data object Cancel : ConfirmResult
		data object OK : ConfirmResult
	}
	
	var title by mutableStateOf("")
	var text by mutableStateOf("")
	
	override fun before() {
		title = ""
		text = ""
	}
	
	@Composable
	override fun Content(scope: CoroutineScope) {
		Card {
			Column(
				modifier = Modifier.padding(16.dp)
			) {
				Text(
					text = title,
					style = Typography.headlineSmall,
					textAlign = TextAlign.Center,
					modifier = Modifier.fillMaxWidth()
				)
				
				Spacer(Modifier.height(8.dp))
				
				Text(text)
				
				Spacer(Modifier.height(8.dp))
				
				Row {
					TextButton(
						onClick = {
							scope.launch {
								done(false)
							}
						},
						modifier = Modifier.weight(1f)
					) {
						Text(stringResource(R.string.btn_cancel))
					}
					
					TextButton(
						onClick = {
							scope.launch {
								done(true)
							}
						},
						modifier = Modifier.weight(1f)
					) {
						Text(stringResource(R.string.btn_ok))
					}
				}
			}
		}
	}
	
	override suspend fun onDismissRequest() = done(false)
	override fun createResult(result: Boolean): ConfirmResult = if (result) ConfirmResult.OK else ConfirmResult.Cancel
}
